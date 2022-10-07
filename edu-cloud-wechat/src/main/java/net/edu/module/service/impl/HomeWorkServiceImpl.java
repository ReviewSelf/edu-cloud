package net.edu.module.service.impl;

import net.edu.module.dao.HomeWorkDao;
import net.edu.module.service.HomeWorkService;
import net.edu.module.vo.HomeWorkVO;
import net.edu.module.vo.WxChoiceProblemVO;
import net.edu.module.vo.WxFillProblemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年09月30日 17:00
 */
@Service
public class HomeWorkServiceImpl implements HomeWorkService {

    @Autowired
    private HomeWorkDao homeWorkDao;
    public List<HomeWorkVO> getStudentHomeWork(String studentId){
        List<HomeWorkVO> homeWork=homeWorkDao.getStudentHomeWork(studentId);
        return homeWork;
    }

   public List<WxChoiceProblemVO> getChoiceProblemInfo(String problemId){
        List<WxChoiceProblemVO> wxChoiceProblemVOS=homeWorkDao.getChoiceProblemInfo(problemId);
        return  wxChoiceProblemVOS;
   }

   public WxFillProblemVO GetFillProblemInfo(String problemId){
        WxFillProblemVO wxFillProblemVOS=homeWorkDao.GetFillProblemInfo(problemId);
        return  wxFillProblemVOS;
   }
}
