package net.edu.module.service.impl;




import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import net.edu.framework.common.constant.Constant;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.utils.TreeUtils;
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


    @Override
    public void save(KnowledgePointVO vo) {
        KnowledgePointEntity entity = KnowledgePointConvert.INSTANCE.convert(vo);
        // 保存知识点
        baseMapper.insert(entity);
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
    }

    @Override
    public void delete(Long id) {
        // 删除菜单
        removeById(id);
    }

    @Override
    public List<KnowledgePointVO> getKpList() {
        List<KnowledgePointEntity> menuList = baseMapper.getKpList();
        return TreeUtils.build(KnowledgePointConvert.INSTANCE.convertList(menuList), Constant.ROOT);
    }

    @Override
    public Long getSubKpCount(Long pid) {
        return count(new LambdaQueryWrapper<KnowledgePointEntity>().eq(KnowledgePointEntity::getPid, pid));
    }

}