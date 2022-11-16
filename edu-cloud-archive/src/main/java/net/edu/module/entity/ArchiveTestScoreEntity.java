package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

import net.edu.framework.mybatis.entity.BaseEntity;


/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("archive_test_score")
public class ArchiveTestScoreEntity extends BaseEntity {
	/**
	* 学号
	*/
	private String stuId;

	/**
	* 得分
	*/
	private String score;

	/**
	* 学生姓名
	*/
	private String stuName;

	/**
	* 评测id
	*/
	private Long testId;

}