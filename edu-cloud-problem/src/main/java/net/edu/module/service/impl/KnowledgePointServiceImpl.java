package net.edu.module.service.impl;





import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.constant.Constant;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.common.utils.TreeUtils;
import net.edu.framework.common.utils.YouBianCodeUtil;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.KnowledgePointConvert;
import net.edu.module.dao.KnowledgePointDao;
import net.edu.module.entity.KnowledgePointEntity;
import net.edu.module.service.KnowledgePointService;
import net.edu.module.vo.KnowledgePointVO;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author: 马佳浩
 * @Date: 2022/9/6 13:15
 * @Version: 1.0
 * @Description:
 */
@Service
@AllArgsConstructor
public class KnowledgePointServiceImpl extends BaseServiceImpl<KnowledgePointDao, KnowledgePointEntity> implements KnowledgePointService {

    private final RedisUtils redisUtils;




    /**
     * 分成三种情况
     * 1.数据库无数据 调用YouBianCodeUtil.getNextYouBianCode(null);
     * 2.添加子节点，无兄弟元素 YouBianCodeUtil.getSubYouBianCode(parentCode,null);
     * 3.添加子节点有兄弟元素 YouBianCodeUtil.getNextYouBianCode(lastCode);
     * @param vo
     */
    @Override
    public void save(KnowledgePointVO vo) {
        KnowledgePointEntity entity = KnowledgePointConvert.INSTANCE.convert(vo);
        //找同类确定上一个最大code
        KnowledgePointEntity bEntity= baseMapper.selectBrotherEntity(vo.getPid(), vo.getCode());
        if (bEntity==null) {
            if (0==vo.getPid()) {
                //情况1
                entity.setCode(YouBianCodeUtil.getNextYouBianCode(null));
                entity.setLevel(1);
            } else {
                //情况2
                KnowledgePointEntity parent =  baseMapper.selectById(vo.getPid());
                if(parent==null){
                    entity.setCode(YouBianCodeUtil.getSubYouBianCode("", null));
                }else {
                    entity.setCode(YouBianCodeUtil.getSubYouBianCode(parent.getCode(), null));
                }
                entity.setLevel(parent.getLevel()+1);
            }
        }else {
            //情况三
            entity.setCode(YouBianCodeUtil.getNextYouBianCode(bEntity.getCode()));
            entity.setLevel(bEntity.getLevel());
        }
        // 保存知识点
        baseMapper.insert(entity);
        redisUtils.del(RedisKeys.getKnowledgePointKey());
    }

    @Override
    public void update(KnowledgePointVO vo) {
        KnowledgePointEntity entity = KnowledgePointConvert.INSTANCE.convert(vo);
        // 上级知识点不能为自己
        if(entity.getId().equals(entity.getPid())){
            throw new ServerException("上级知识点不能为自己");
        }
        // 更新菜单
        updateById(entity);
        redisUtils.del(RedisKeys.getKnowledgePointKey());
    }

    @Override
    public void delete(Long id) {
        // 删除菜单
        removeById(id);
        redisUtils.del(RedisKeys.getKnowledgePointKey());
    }

    @Override
    public List<KnowledgePointVO> getKpList() {
        List<KnowledgePointEntity> menuList= (List<KnowledgePointEntity>) redisUtils.get(RedisKeys.getKnowledgePointKey(),RedisUtils.MIN_TEN_EXPIRE);
        if(menuList==null){
            menuList = baseMapper.getKpList();
            redisUtils.set(RedisKeys.getKnowledgePointKey(),menuList,RedisUtils.MIN_TEN_EXPIRE);
        }
        return TreeUtils.build(KnowledgePointConvert.INSTANCE.convertList(menuList), Constant.ROOT);
    }

    @Override
    public Long getSubKpCount(Long pid) {
        return count(new LambdaQueryWrapper<KnowledgePointEntity>().eq(KnowledgePointEntity::getPid, pid));
    }

}
