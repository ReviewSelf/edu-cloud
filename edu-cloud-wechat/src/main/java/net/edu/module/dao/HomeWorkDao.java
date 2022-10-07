package net.edu.module.dao;

import net.edu.module.vo.HomeWorkVO;
import net.edu.module.vo.WxChoiceProblemVO;
import net.edu.module.vo.WxFillProblemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeWorkDao {
    List<HomeWorkVO> getStudentHomeWork(String studentId);
    List<WxChoiceProblemVO> getChoiceProblemInfo(String problemId);

    WxFillProblemVO GetFillProblemInfo(String problemId);


}
