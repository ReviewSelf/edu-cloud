package net.edu.module.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.utils.IpUtils;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ExamIPConvert;
import net.edu.module.dao.ExamIPDao;
import net.edu.module.dao.ExamIPDao;
import net.edu.module.entity.ExamIPEntity;
import net.edu.module.entity.ExamIPEntity;
import net.edu.module.query.ExamIPQuery;
import net.edu.module.query.ExamIPQuery;
import net.edu.module.service.ExamIPService;
import net.edu.module.service.ExamIPService;
import net.edu.module.vo.ExamIPVO;
import net.edu.module.vo.ExamIPVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/10/12 9:57
 * @Version: 1.0
 * @Description:
 */
@Service
@AllArgsConstructor
public class ExamIPServiceImpl  extends BaseServiceImpl<ExamIPDao, ExamIPEntity> implements ExamIPService {
    private final RedisUtils redisUtils;

    @Override
    public List<ExamIPVO> list(ExamIPQuery query) {
        List<ExamIPEntity> list=null;
        list= (List<ExamIPEntity>) redisUtils.get(RedisKeys.getExamIp(query.getExamId()),RedisUtils.MIN_TEN_EXPIRE);
        if(list==null){
            LambdaQueryWrapper<ExamIPEntity> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(true, ExamIPEntity::getExamId, query.getExamId());
            list = baseMapper.selectList(wrapper);
            redisUtils.set(RedisKeys.getExamIp(query.getExamId()),list,RedisUtils.HOUR_ONE_EXPIRE);
        }
        return ExamIPConvert.INSTANCE.convertList(list);
    }



    @Override
    public void save(ExamIPVO vo) {
        ExamIPEntity entity = ExamIPConvert.INSTANCE.convert(vo);
        baseMapper.insert(entity);

//        更新缓存
        List<ExamIPEntity> list=null;
        list= (List<ExamIPEntity>) redisUtils.get(RedisKeys.getExamIp(vo.getExamId()),RedisUtils.MIN_TEN_EXPIRE);
        if(list!=null){
            list.add(entity);
            redisUtils.set(RedisKeys.getExamIp(vo.getExamId()),list,RedisUtils.HOUR_ONE_EXPIRE);
        }
    }

    @Override
    public void update(ExamIPVO vo) {
        ExamIPEntity entity = ExamIPConvert.INSTANCE.convert(vo);
        redisUtils.del(RedisKeys.getExamIp(entity.getExamId()));
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        ExamIPEntity entity = baseMapper.selectById(idList.get(0));
        redisUtils.del(RedisKeys.getExamIp(entity.getExamId()));
        removeByIds(idList);
    }

    @Override
    public Boolean ipJudge(Long ExamId, String ip){

        //获取ip白名单
        List<ExamIPVO> ipList=list(new ExamIPQuery(ExamId));
        //白名单校验
        if(!CollectionUtil.isEmpty(ipList)){
            for (ExamIPVO item:ipList){
                if(IpUtils.ipExistsInRange(ip,item.getIpRange())){
                    return true;
                }
            }
            return false;
        }
        return true;

    }
}
