package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.LessonChatLogEntity;
import net.edu.module.query.LessonChatLogQuery;
import net.edu.module.query.LessonQuery;
import net.edu.module.vo.LessonChatLogVO;
import net.edu.module.vo.LessonVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 马佳浩
 * @date 2022/12/9 09:13:26
 * @description
 */
@Mapper
public interface LessonChatLogDao  extends BaseDao<LessonChatLogEntity> {
    List<LessonChatLogVO> selectPage(@Param("query") LessonChatLogQuery query);
}
