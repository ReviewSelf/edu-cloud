package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.EnrollClassEntity;
import net.edu.module.entity.EnrollUserEntity;
import net.edu.module.query.EnrollUserQuery;
import net.edu.module.vo.EnrollUserVO;
import org.apache.ibatis.annotations.Mapper;

/**
* XinXiHeShi
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-05
*/
@Mapper
public interface EnrollUserDao extends BaseDao<EnrollUserEntity> {
    IPage<EnrollUserVO> getEnrollUserByPage(Page<EnrollUserVO> page, EnrollUserQuery query);


    void deleteEnrollUser(long id);
}
