package net.edu.module.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.TeachPayEntity;
import net.edu.module.query.TeachPayQuery;
import net.edu.module.vo.TeachPayVO;
import org.apache.ibatis.annotations.Mapper;

/**
* 流水账管理
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-09-16
*/
@Mapper
public interface TeachPayDao extends BaseDao<TeachPayEntity> {

    IPage<TeachPayVO> page(Page<TeachPayVO> page, TeachPayQuery query);

    TeachPayVO getPaymentDetail(Long id);


}