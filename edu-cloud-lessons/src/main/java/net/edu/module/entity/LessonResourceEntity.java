package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;
import net.edu.framework.mybatis.entity.BaseEntity;

/**
 * 教学日历资源表
 *
 * @author 马佳浩 babamu@126.com
 * @since 1.0.0 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("lesson_resource")
public class LessonResourceEntity extends BaseEntity {
	/**
	* 名称
	*/
	private String name;

	/**
	* 路径
	*/
	private String filePath;

	/**
	* 资源类型，见字典表
	*/
	private Integer fileType;

	/**
	* 来源，1=教学计划生成（不可删除），2=老师添加可删除
	*/
	private Integer source;

}