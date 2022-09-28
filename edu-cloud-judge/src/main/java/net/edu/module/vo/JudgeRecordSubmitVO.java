package net.edu.module.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;




/**
 * @Author: 马佳浩
 * @Date: 2022/9/12 12:53
 * @Version: 1.0
 * @Description:
 */
@Data
public class JudgeRecordSubmitVO {
    @TableId
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
    // 0=未判题，3=正确，4=错误
    private Integer submitStatus;

}
