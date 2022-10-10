package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.ChoiceProblemDao;
import net.edu.module.dao.HomeWorkDao;
import net.edu.module.entity.ChoiceProblemEntity;
import net.edu.module.entity.HomeWorkEntity;
import net.edu.module.query.HomeWorkQuery;
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
@AllArgsConstructor
public class HomeWorkServiceImpl extends BaseServiceImpl<HomeWorkDao,HomeWorkEntity> implements HomeWorkService {

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

   public void  changeProblemStatus(String problemId) {
       homeWorkDao.changeProblemStatus(problemId);
   }

    public  PageResult<HomeWorkVO> getStudentHomeWorkPage(HomeWorkQuery query){

        Page<HomeWorkVO> page=new Page<>(query.getPage(), query.getLimit());
        IPage<HomeWorkVO> list=baseMapper.getStudentHomeWorkPage(page,query);
        return new PageResult<>(list.getRecords(),list.getTotal());
    }
}
