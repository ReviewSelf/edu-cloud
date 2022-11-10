package net.edu.module.dao;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAbilityDao {

    int insertUserAbility(Long abilityId, Long userId);
}
