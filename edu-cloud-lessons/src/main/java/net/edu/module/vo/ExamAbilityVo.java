package net.edu.module.vo;


import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

import java.util.List;

@Data
public class ExamAbilityVo {

    private List<ExamVO> examAbilityList;

    private List<Long> examIdList;
}
