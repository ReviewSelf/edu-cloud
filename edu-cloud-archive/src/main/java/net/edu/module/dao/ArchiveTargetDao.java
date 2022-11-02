package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveTargetEntity;
import net.edu.module.query.ArchiveTargetQuery;
import net.edu.module.vo.ArchiveTargetVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* target
*
* @author qxd babamu@126.com
* @since 1.0.0 2022-10-24
*/
@Mapper
public interface ArchiveTargetDao extends BaseDao<ArchiveTargetEntity> {

    IPage<ArchiveTargetVO> selectArchiveTargetByPage(Page<ArchiveTargetVO> page, @Param("query") ArchiveTargetQuery query);

    ArchiveTargetVO selectArchiveTargetById(Long id);

    void updateArchiveTarget(ArchiveTargetVO vo);

    void insertArchiveTarget(ArchiveTargetVO vo);
    List<ArchiveTargetVO> selectName();

    List<ArchiveTargetVO> selectArchiveTargetByGraduateId(Long graduateId);

}
