package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ArchiveCourseEntity;
import net.edu.module.query.ArchiveCourseQuery;
import net.edu.module.vo.ArchiveCourseVO;
import net.edu.module.vo.ArchiveTargetVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* 能力课程
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-25
*/
@Mapper
public interface ArchiveCourseDao extends BaseDao<ArchiveCourseEntity> {

    IPage<ArchiveCourseVO> selectArchiveCourseByPage(Page<ArchiveCourseVO> page, ArchiveCourseQuery query);

    List<ArchiveCourseVO> selectName();
}
