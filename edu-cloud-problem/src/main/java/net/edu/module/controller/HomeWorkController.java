package net.edu.module.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.dao.HomeWorkDao;
import net.edu.module.query.ChoiceProblemQuery;
import net.edu.module.query.HomeWorkQuery;
import net.edu.module.service.HomeWorkService;
import net.edu.module.vo.ChoiceProblemVO;
import net.edu.module.vo.HomeWorkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年10月05日 19:09
 */
@RestController
@RequestMapping("homeWork")
@Tag(name="消息推送")
@AllArgsConstructor
@Slf4j
public class HomeWorkController {
    @Autowired
    private final HomeWorkService homeWorkService;

    @Autowired
    private final HomeWorkDao homeWorkDao;

    /**
     * 根据学生id获取对应课后作业
     *
     */
    @GetMapping("/homeWork")
    public List<HomeWorkVO> getStudentHomeWork(String studentId){

        List<HomeWorkVO> homeWorkVO=homeWorkService.getStudentHomeWork(studentId);
        return homeWorkVO;
    }



    /**
     * 根据学生id分页获取对应课后作业
     *
     */
    @GetMapping("/homeWorkPage")
    public Result<PageResult<HomeWorkVO>> getStudentHomeWorkPage(@Valid HomeWorkQuery query){

        log.info(query.toString());
        PageResult<HomeWorkVO> page=homeWorkService.getStudentHomeWorkPage(query);

        log.info(page.toString());
        return Result.ok(page);

    }

    /**
     * 改变完成状态
     */
    @GetMapping("/changeProblemStatus")
    public void changeChoiceProblemStatus(String problemId){
        homeWorkService.changeProblemStatus(problemId);
    }
}
