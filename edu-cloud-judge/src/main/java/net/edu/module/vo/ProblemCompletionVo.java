package net.edu.module.vo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.util.Date;
import java.util.List;

@Data
public class ProblemCompletionVo {
    private Long id;
    private Long problemId;
    private Integer problemType;
    private Integer source;
    private Long sourceId;
    @TableField(fill = FieldFill.INSERT)
    private Long userId;
    private String submitContent;
    private String submitImg;
    private Integer languageType;
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date submitTime;
    //0=未判题，3=正确，4=错误
    private Integer submitStatus;
    private Integer judgeType;
    private Integer judgeUser;
    private String judgeReason;
    private List<JudgeSampleResultVO> sampleVoList;
}
