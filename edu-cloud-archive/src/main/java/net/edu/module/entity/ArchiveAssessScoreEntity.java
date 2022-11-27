package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.List;


/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("archive_assess_score")
public class ArchiveAssessScoreEntity extends BaseEntity {
	/**
	 * id
	 */
	private Long id;

	/**
	* 学号
	*/
	private String stuId;

	/**
	* 得分
	*/
	private Double score;

	/**
	* 学生姓名
	*/
	private String stuName;

	/**
	* 课程id
	*/
	private Long courseId;

	/**
	 * 考核点id
	 */
	private Long assessId;


}