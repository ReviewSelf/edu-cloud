package net.edu.module.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.util.Date;

/**
 * @author 马佳浩
 * @date 2022/12/9 10:33:19
 * @description
 */
@Data
public class LessonChatLogVO {

    private Long id;


    private Long userId;

    private String username;

    private Long lessonId;

    private String msg;

    private String createTime;
}
