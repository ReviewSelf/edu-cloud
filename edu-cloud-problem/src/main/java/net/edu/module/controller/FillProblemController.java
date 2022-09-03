package net.edu.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.module.entity.CodeProblemEntity;
import net.edu.module.query.ProblemQuery;
import net.edu.module.service.CodeProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 */
@RestController
@RequestMapping("/fill")
@Tag(name="问题列表接口")
@AllArgsConstructor
public class FillProblemController {




}
