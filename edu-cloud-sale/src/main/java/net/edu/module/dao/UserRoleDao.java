package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色关系
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface UserRoleDao extends BaseDao<UserRoleEntity> {

    /**
     * 角色ID列表
     * @param userId  用户ID
     *
     * @return  返回角色ID列表
     */
    List<Long> selectRoleIdList(@Param("userId") Long userId);


}
