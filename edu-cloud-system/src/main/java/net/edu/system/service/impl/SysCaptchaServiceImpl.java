package net.edu.system.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.system.service.SysCaptchaService;
import net.edu.system.vo.SysCaptchaVO;
import org.springframework.stereotype.Service;

/**
 * 验证码
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class SysCaptchaServiceImpl implements SysCaptchaService {

    private final RedisUtils redisUtils;

    @Override
    public SysCaptchaVO generate() {
        // 生成验证码key
        String key = UUID.randomUUID().toString();

        // 生成验证码
        SpecCaptcha captcha = new SpecCaptcha(150, 40);
        captcha.setLen(5);
        captcha.setCharType(Captcha.TYPE_DEFAULT);
        String image = captcha.toBase64();

        // 保存到缓存
        String redisKey = RedisKeys.getCaptchaKey(key);
        redisUtils.set(redisKey, captcha.text(), 300);

        // 封装返回数据
        SysCaptchaVO captchaVO = new SysCaptchaVO();
        captchaVO.setKey(key);
        captchaVO.setImage(image);

        return captchaVO;
    }

    @Override
    public boolean validate(String key, String code) {
        if (StrUtil.isBlank(key) || StrUtil.isBlank(code)) {
            return false;
        }

        // 获取验证码
        String captcha = getCache(key);

        // 效验成功
        return code.equalsIgnoreCase(captcha);
    }

    private String getCache(String key) {
        key = RedisKeys.getCaptchaKey(key);
        String captcha = (String) redisUtils.get(key);
        // 删除验证码
        if (captcha != null) {
            redisUtils.del(key);
        }

        return captcha;
    }
}
