package net.edu.module.vo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import net.edu.module.dao.JudgeRecordSampleDao;

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
    //0=未判题，3=正确，4=错误
    private Integer submitStatus;
    private Integer judgeType;
    private Integer judgeUser;
    private String judgeReason;
    private String answer;
    private List<RecordSampleVo> recordSampleVoList;
}
