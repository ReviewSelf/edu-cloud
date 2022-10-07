package net.edu.module.service;

import net.edu.module.vo.HomeWorkVO;
import net.edu.module.vo.WxChoiceProblemVO;
import net.edu.module.vo.WxFillProblemVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomeWorkService {

    List<HomeWorkVO> getStudentHomeWork(String studentId);

    List<WxChoiceProblemVO> getChoiceProblemInfo(String problemId);
    WxFillProblemVO GetFillProblemInfo(String problemId);

    void changeProblemStatus(String problemId);
}
