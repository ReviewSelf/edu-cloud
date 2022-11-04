package net.edu.module.service.impl;


import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.module.dao.AbilityPointDao;
import net.edu.module.dao.AbilityRelatedDao;
import net.edu.module.service.AbilityPointService;
import net.edu.module.vo.AbilityMapVO;
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

    private final RedisUtils redisUtils;

    @Override
    public AbilityMapVO getAbilityMap(Long id){
        AbilityMapVO abilityMapVO=null;
        abilityMapVO= (AbilityMapVO) redisUtils.get(RedisKeys.getAbilityMap(id));
        if(abilityMapVO==null){
            abilityMapVO=new AbilityMapVO();
            abilityMapVO.setAbilityPointVOS(abilityPointDao.selectList(id));
            abilityMapVO.setAbilityRelatedVOS(abilityRelatedDao.selectRelatedList(id));
            redisUtils.set(RedisKeys.getAbilityMap(id),abilityMapVO,RedisUtils.HOUR_ONE_EXPIRE);
        }
        return abilityMapVO;

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(List<String> list,Long id) {
        abilityPointDao.insertPoint(list,id);
        redisUtils.del(RedisKeys.getAbilityMap(id));
    }

    @Override
    public void update(AbilityPointVO vo) {
        abilityPointDao.updatePoint(vo);
        redisUtils.del(RedisKeys.getAbilityMap(vo.getAbilityId()));
    }

    @Override
    public void delete(Long id) {
        AbilityPointVO vo=abilityPointDao.selectPointInfo(id);
        abilityPointDao.deletePoint(id);
        redisUtils.del(RedisKeys.getAbilityMap(vo.getAbilityId()));
    }




    @Override
    public void deleteRelated(Long id) {
        AbilityRelatedVO vo=abilityRelatedDao.selectRelated(id);
        abilityRelatedDao.deleteRelated(id);
        redisUtils.del(RedisKeys.getAbilityMap(vo.getAbilityId()));
    }

    @Override
    public void saveRelated(AbilityRelatedVO vo) {
        abilityRelatedDao.insertRelated(vo);
        redisUtils.del(RedisKeys.getAbilityMap(vo.getAbilityId()));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateList(List<AbilityPointVO> list) {
        for(int i= 0;i<list.size();i++){
            abilityPointDao.updatePoint(list.get(i));
        }
        redisUtils.del(RedisKeys.getAbilityMap(list.get(0).getAbilityId()));
    }
}
