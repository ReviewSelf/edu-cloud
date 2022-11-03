package net.edu.module.service.impl;


import lombok.AllArgsConstructor;
import net.edu.module.dao.AbilityPointDao;
import net.edu.module.dao.AbilityRelatedDao;
import net.edu.module.service.AbilityPointService;
import net.edu.module.vo.AbilityPointVO;
import net.edu.module.vo.AbilityRelatedVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AbilityPointServiceImpl implements AbilityPointService {
    private final AbilityPointDao abilityPointDao;
    private final AbilityRelatedDao abilityRelatedDao;

    @Override
    public List<AbilityPointVO> getList(Long id) {
        List<AbilityPointVO> list = abilityPointDao.selectList(id);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(List<String> list,Long id) {
        abilityPointDao.insertPoint(list,id);
    }

    @Override
    public void update(AbilityPointVO vo) {
        abilityPointDao.updatePoint(vo);
    }

    @Override
    public void delete(Long id) {
        abilityPointDao.deletePoint(id);
    }

    @Override
    public AbilityPointVO getPointInfo(Long id) {
        return abilityPointDao.selectPointInfo(id);
    }

    @Override
    public void deleteRelated(Long id) {
        abilityRelatedDao.deleteRelated(id);
    }

    @Override
    public void saveRelated(AbilityRelatedVO vo) {
        abilityRelatedDao.insertRelated(vo);
    }

    @Override
    public List<AbilityPointVO> relatedList(Long id) {
        return abilityRelatedDao.selectRelated(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateList(List<AbilityPointVO> list) {
        for(int i= 0;i<list.size();i++){
            abilityPointDao.updatePoint(list.get(i));
        }
    }
}
