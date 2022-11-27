package net.edu.module.service.impl;

import lombok.AllArgsConstructor;
import net.edu.framework.common.constant.Constant;
import net.edu.framework.common.utils.TreeUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.convert.AbilityConvert;
import net.edu.module.dao.AbilityDao;
import net.edu.module.dao.UserAbilityDao;
import net.edu.module.entity.AbilityEntity;
import net.edu.module.service.AbilityService;
import net.edu.module.vo.AbilityMapVO;
import net.edu.module.vo.AbilityPointVO;
import net.edu.module.vo.AbilityUserVo;
import net.edu.module.vo.AbilityVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 能力纬度图
 *
 * @author sqw 
 * @since 1.0.0 2022-10-27
 */
@Service
@AllArgsConstructor
public class AbilityServiceImpl extends BaseServiceImpl<AbilityDao, AbilityEntity> implements AbilityService {

    private final AbilityPointServiceImpl abilityPointService;
    private final UserAbilityDao userAbilityDao;

    @Override
    public List<AbilityVO> getAbilityList() {
        List<AbilityEntity> list=baseMapper.selectList(null);

        return TreeUtils.build(AbilityConvert.INSTANCE.convertList(list), Constant.ROOT);
    }

    @Override
    public List<AbilityVO> getAbilityItemList() {
        List<AbilityVO> list = baseMapper.selectAbilityItemList(SecurityUser.getUser().getAbilityId(),SecurityUser.getUserId());
        return  list;
    }


    @Override
    public void save(AbilityVO vo) {
        AbilityEntity entity = AbilityConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(AbilityVO vo) {
        AbilityEntity entity = AbilityConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean judgeUnlockAbility(Long lastAbilityId,Long abilityId,Long userId) {
        if(lastAbilityId == -1){
            //如果当前用户要点亮当前大类中的第一个能力，直接解锁，将其插入user_ability表中
            userAbilityDao.insertUserAbility(abilityId,userId);
            return true;
        }else{
            //获取当前要解锁能力图的上一个能力图，判断此能力图是否达标
            AbilityMapVO abilityMapVO =  abilityPointService.getAbilityMap(lastAbilityId,userId);
            for(AbilityPointVO abilityPointVO:abilityMapVO.getAbilityPointVOS()){
                if(abilityPointVO.getStandardNum()<3 || abilityPointVO.getStandardNum() == null){
                    return false;
                }
            }
            //至此判断出能力已达标插入
            userAbilityDao.insertUserAbility(abilityId,userId);
            return true;
        }
    }

    @Override
    public AbilityUserVo getUserAbility(Long userId) {
        AbilityUserVo vo = new AbilityUserVo();
        vo.setAbilityId(baseMapper.selectAbilityIdFromSysUser(userId));
        vo.setChildAbilityId(userAbilityDao.selectChildAbilityId(userId));
        return vo;
    }


}