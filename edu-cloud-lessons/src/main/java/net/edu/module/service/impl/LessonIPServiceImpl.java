package net.edu.module.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.HttpContextUtils;
import net.edu.framework.common.utils.IpUtils;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.common.utils.Result;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.LessonIPConvert;
import net.edu.module.dao.LessonIPDao;
import net.edu.module.entity.LessonEntity;
import net.edu.module.entity.LessonIPEntity;
import net.edu.module.query.LessonIPQuery;
import net.edu.module.service.LessonIPService;
import net.edu.module.vo.LessonIPVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 1
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
@Service
@AllArgsConstructor
public class LessonIPServiceImpl extends BaseServiceImpl<LessonIPDao, LessonIPEntity> implements LessonIPService {


    private final RedisUtils redisUtils;

    @Override
    public List<LessonIPVO> list(LessonIPQuery query) {
        List<LessonIPEntity> list=null;
        list= (List<LessonIPEntity>) redisUtils.get(RedisKeys.getLessonIp(query.getLessonId()),RedisUtils.MIN_TEN_EXPIRE);
        if(list==null){
            LambdaQueryWrapper<LessonIPEntity> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(true, LessonIPEntity::getLessonId, query.getLessonId());
            list = baseMapper.selectList(wrapper);
            redisUtils.set(RedisKeys.getLessonIp(query.getLessonId()),list,RedisUtils.HOUR_ONE_EXPIRE);
        }
          return LessonIPConvert.INSTANCE.convertList(list);
    }



    @Override
    public void save(LessonIPVO vo) {
        LessonIPEntity entity = LessonIPConvert.INSTANCE.convert(vo);
        baseMapper.insert(entity);

//        更新缓存
        List<LessonIPEntity> list=null;
        list= (List<LessonIPEntity>) redisUtils.get(RedisKeys.getLessonIp(vo.getLessonId()),RedisUtils.MIN_TEN_EXPIRE);
        if(list!=null){
            list.add(entity);
            redisUtils.set(RedisKeys.getLessonIp(vo.getLessonId()),list,RedisUtils.HOUR_ONE_EXPIRE);
        }
    }

    @Override
    public void update(LessonIPVO vo) {
        LessonIPEntity entity = LessonIPConvert.INSTANCE.convert(vo);
        redisUtils.del(RedisKeys.getLessonIp(entity.getLessonId()));
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        LessonIPEntity entity = baseMapper.selectById(idList.get(0));
        redisUtils.del(RedisKeys.getLessonIp(entity.getLessonId()));
        removeByIds(idList);
    }

    @Override
    public Boolean ipJudge(Long lessonId, String ip){

        //获取ip白名单
        List<LessonIPVO> ipList=list(new LessonIPQuery(lessonId));
        //白名单校验
        if(!CollectionUtil.isEmpty(ipList)){
            for (LessonIPVO item:ipList){
                if(IpUtils.ipExistsInRange(ip,item.getIpRange())){
                    return true;
                }
            }
            return false;
        }
        return true;

    }

}