package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.common.utils.Result;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachClassUserEntity;
import net.edu.module.entity.TeachStudentEntity;
import net.edu.module.query.TeachClassUserQuery;
import net.edu.module.vo.TeachClassStudentVo;
import net.edu.module.vo.TeachClassUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.util.Date;

/**
* 班级用户表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-16
*/
@Mapper
public interface TeachClassUserDao extends BaseDao<TeachClassUserEntity> {

    IPage<TeachClassUserVO> page(Page<TeachClassUserVO> page , @Param("query") TeachClassUserQuery query);

    void updateQuitClass(Long classId, Long userId, Date quitTime);
    int insertClassUser(@Param("list") List list,@Param("classId") Long classId);

    List<Long> selectUserIdList(Long classId);

    List<TeachStudentEntity> selectStudent(Long id);

    int updateHomeworkTimes(Long userId, Long classId, Integer num);
}
