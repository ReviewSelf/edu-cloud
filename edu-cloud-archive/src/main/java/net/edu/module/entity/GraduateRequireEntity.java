package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;


/**
 * 毕业要求
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-20
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("archive_graduate_require")
public class GraduateRequireEntity extends BaseEntity {
	/**
	* 标题
	*/
	private String title;

	/**
	* 描述
	*/
	private String content;

	/**
	 * 年份
	 */
	private String grade;

	/**
	* 专业
	*/
	private Long major;

	/**
	* 学院
	*/
	private Long college;

	/**
	* 学校
	*/
	private Long school;

}
