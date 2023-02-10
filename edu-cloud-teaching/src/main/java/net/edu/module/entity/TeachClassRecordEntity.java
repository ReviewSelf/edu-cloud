package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * 学生班级记录
 *
 * @author sqw 
 * @since 1.0.0 2023-02-08
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_class_record")
public class TeachClassRecordEntity extends BaseEntity {
	/**
	* 1代表插班，2代表换班，3代表退班
	*/
	private Integer type;

	/**
	* 记录时间
	*/
	private Date recordTime;

	/**
	* 学生id
	*/
	private Long studentId;

	/**
	 * 学生姓名
	 */
	private String studentName;

	/**
	* 教务老师id
	*/
	private Long teacherId;

	/**
	 * 教务老师名称
	 */
	private String teacherName;

	/**
	* 原有班级id
	*/
	private Long oldClassId;

	/**
	 * 原有班级名称
	 */
	private String oldClassName;

	/**
	* 原有课堂id
	*/
	private Long oldLessonId;

	/**
	* 新班级id
	*/
	private Long newClassId;

	/**
	 * 新班级名称
	 */
	private String newClassName;

	/**
	* 新课堂id
	*/
	private Long newLessonId;

}