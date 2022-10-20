package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.HomeWorkEntity;
import net.edu.module.query.HomeWorkQuery;
import net.edu.module.vo.HomeWorkVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HomeWorkDao extends BaseDao<HomeWorkEntity> {




    void  changeProblemStatus(String problemId);

    IPage<HomeWorkVO> getStudentHomeWorkPage(Page<HomeWorkVO> page, @Param("query")HomeWorkQuery query);



}
