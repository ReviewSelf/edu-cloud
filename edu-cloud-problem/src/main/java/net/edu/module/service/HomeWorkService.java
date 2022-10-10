package net.edu.module.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ChoiceProblemEntity;
import net.edu.module.entity.HomeWorkEntity;
import net.edu.module.query.ChoiceProblemQuery;
import net.edu.module.query.HomeWorkQuery;
import net.edu.module.vo.ChoiceProblemVO;
import net.edu.module.vo.HomeWorkVO;
import net.edu.module.vo.WxChoiceProblemVO;
import net.edu.module.vo.WxFillProblemVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HomeWorkService  extends BaseService<HomeWorkEntity>{

    List<HomeWorkVO> getStudentHomeWork(String studentId);

    List<WxChoiceProblemVO> getChoiceProblemInfo(String problemId);
    WxFillProblemVO GetFillProblemInfo(String problemId);

    void changeProblemStatus(String problemId);


    PageResult<HomeWorkVO> getStudentHomeWorkPage(HomeWorkQuery query);
}
