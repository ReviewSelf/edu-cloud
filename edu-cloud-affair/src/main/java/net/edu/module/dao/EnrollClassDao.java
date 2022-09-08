package net.edu.module.dao;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.EnrollClassEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 班级发布
*
* @author 翁瑞辰 
* @since  2022-09-06
*/
@Mapper
public interface EnrollClassDao extends BaseDao<EnrollClassEntity> {
    void deleteEnrollClass(long id);

    IPage<EnrollClassEntity> getEnrollClassByPage(Page<EnrollClassEntity> page);

    IPage<EnrollClassEntity> select(Page<EnrollClassEntity> page,String className);

    void updateStatus(Long id);
}