package net.edu.module.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.OrgEntity;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.UserQuery;
import net.edu.module.vo.AllTeacherVo;
import net.edu.module.vo.UserVO;
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
public interface UserDao extends BaseDao<UserEntity> {

//	List<UserEntity> getList(Map<String, Object> params);

	List<UserEntity> getTeacherList(Map<String, Object> params);

	IPage<UserVO> SelectStudentList(Page<UserVO> page, UserQuery query);

	UserEntity getById(@Param("id") Long id);

	List<UserEntity> getRoleUserList(Map<String, Object> params);

	default UserEntity getByUsername(String username){
		return this.selectOne(new QueryWrapper<UserEntity>().eq("username", username));
	}

	default UserEntity getByMobile(String mobile){
		return this.selectOne(new QueryWrapper<UserEntity>().eq("mobile", mobile));
	}

	List<AllTeacherVo> getTeacher();

	void updateSubmitCorrectTimes(Long userId,Integer correct);

	String getStudentId(String unionId);

    List<OrgEntity> getOrgList();
}
