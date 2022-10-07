package net.edu.module.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.module.service.HomeWorkService;
import net.edu.module.vo.HomeWorkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class HomeWorkController {
    @Autowired
    private final HomeWorkService homeWorkService;


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
     * 改变完成状态
     */
    @GetMapping("/changeProblemStatus")
    public void changeChoiceProblemStatus(String problemId){
        homeWorkService.changeProblemStatus(problemId);
    }
}
