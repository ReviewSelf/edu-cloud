package net.edu.module.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachClassHoursEntity;
import net.edu.module.vo.TeachClassHoursVO;
import net.edu.module.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

/**
* 课时
*
* @author weng babamu@126.com
* @since 1.0.0 2023-03-02
*/
@Mapper
public interface TeachClassHoursDao extends BaseDao<TeachClassHoursEntity> {

    TeachClassHoursVO getStudentPay(Long id);

}