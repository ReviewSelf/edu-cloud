package net.edu.module.vo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

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
    private Integer submitStatus=0; //0=未判题，3=正确，4=错误
    private Integer judgeType;
    private Integer judgeUser;
    private Integer judgeReason;
    private String answer;
}
