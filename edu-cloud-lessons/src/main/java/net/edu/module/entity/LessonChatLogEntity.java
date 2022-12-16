package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author 马佳浩
 * @date 2022/12/9 09:09:54
 * @description
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("lesson_chat_log")
public class LessonChatLogEntity {
    @TableId
    private Long id;

    @Schema(description = "学生id")
    private Long userId;

    @Schema(description = "课堂id")
    private Long lessonId;

    @Schema(description = "消息内容")
    private String msg;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

}
