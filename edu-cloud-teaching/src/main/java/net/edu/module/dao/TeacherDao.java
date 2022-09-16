package net.edu.module.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface TeacherDao extends BaseDao<UserEntity> {


	List<UserEntity> getTeacherList(Map<String, Object> params);

	UserEntity getById(@Param("id") Long id);

	List<UserEntity> getRoleUserList(Map<String, Object> params);

	default UserEntity getByUsername(String username){
		return this.selectOne(new QueryWrapper<UserEntity>().eq("username", username));
	}

	default UserEntity getByMobile(String mobile){
		return this.selectOne(new QueryWrapper<UserEntity>().eq("mobile", mobile));
	}
}
