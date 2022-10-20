package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.HomeWorkEntity;
import net.edu.module.query.HomeWorkQuery;
import net.edu.module.vo.HomeWorkVO;


import java.util.List;


public interface HomeWorkService extends BaseService<HomeWorkEntity>{




    void changeProblemStatus(String problemId);


    PageResult<HomeWorkVO> getStudentHomeWorkPage(HomeWorkQuery query);
}
