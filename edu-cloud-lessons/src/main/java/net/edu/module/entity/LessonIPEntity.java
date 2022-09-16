package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import net.edu.framework.mybatis.entity.BaseEntity;


/**
 * 1
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("lesson_ip")
public class LessonIPEntity extends BaseEntity {
	/**
	* 课堂id
	*/
	private Long lessonId;

	/**
	* IP段
	*/
	private String ipRange;

	/**
	* 备注
	*/
	private String remark;

}