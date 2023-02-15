package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * 沟通记录表
 *
 * @author sqw 
 * @since 1.0.0 2023-02-05
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_communicate_record")
public class TeachCommunicateRecordEntity extends BaseEntity {
	/**
	* 教务老师id
	*/
	private Long teacherId;

	/**
	 * 教务老师姓名
	 */
	private String teacherName;

	/**
	* 学生id
	*/
	private Long studentId;

	/**
	 * 学生姓名
	 */
	private String studentName;

	/**
	* 沟通对象
	*/
	private String target;

	/**
	* 沟通时间
	*/
	private Date time;

	/**
	* 沟通主题
	*/
	private String theme;

	/**
	* 沟通内容
	*/
	private String content;

}