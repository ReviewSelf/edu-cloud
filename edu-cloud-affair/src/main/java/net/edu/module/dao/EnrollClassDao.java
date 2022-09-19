package net.edu.module.dao;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.EnrollClassEntity;
import net.edu.module.query.EnrollClassQuery;
import net.edu.module.query.EnrollUserQuery;
import net.edu.module.vo.EnrollClassVO;
import net.edu.module.vo.EnrollUserVO;
import org.apache.ibatis.annotations.Mapper;

/**
* 班级发布
*
* @author 翁瑞辰 
* @since  2022-09-06
*/
@Mapper
public interface EnrollClassDao extends BaseDao<EnrollClassEntity> {


    IPage<EnrollClassVO> SelectEnrollClassByPage(Page<EnrollClassVO> page, EnrollClassQuery query);

    void updateStatus(Long id);
    void unUpdateStatus(Long id);
}