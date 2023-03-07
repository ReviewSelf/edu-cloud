package net.edu.module.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.TeacherQuery;
import net.edu.module.query.UserQuery;
import net.edu.module.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
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

	IPage<TeacherVO> selectTeacherList(Page<TeacherVO> page, TeacherQuery query);

	IPage<UserVO> selectStudentList(Page<UserVO> page, UserQuery query);

	UserEntity getById(@Param("id") Long id);

	List<UserEntity> getRoleUserList(Map<String, Object> params);

	default UserEntity getByUsername(String username){
		return this.selectOne(new QueryWrapper<UserEntity>().eq("username", username));
	}

	default UserEntity getByMobile(String mobile){
		return this.selectOne(new QueryWrapper<UserEntity>().eq("mobile", mobile));
	}

	List<AllTeacherVO> getTeacher();

	void updateSubmitCorrectTimes(Long userId,Integer correct);

	String getStudentId(String unionId);

    List<OrgVo> getOrgList();

	List<AffairTeacherVO> getAffairTeacherList(Map<String, Object> params);

	IPage<TeachStudentVO> selectStudents(Page<TeachStudentVO> page, StudentsVO vo);

	void renewAmountSubmit(Long userId, Integer num);

	void deductionAmountSubmit(Long userId, Integer num);

    void updateUserAfterPay(Long userId, BigDecimal payment, Integer balance);

	void increaseAmountSubmit(Long userId, Integer num);

	void updateUserClassHours(TeachClassHoursVO vo,Long userId);
}
