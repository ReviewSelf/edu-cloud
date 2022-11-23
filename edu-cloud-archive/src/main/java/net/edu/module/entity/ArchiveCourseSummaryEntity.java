package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;

/**
 * 课程总结
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-14
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("archive_course_summary")
public class ArchiveCourseSummaryEntity {
	/**
	* 课程实施总结编号
	*/
	private Long id;

	/**
	* 课程编号
	*/
	private Long courseId;

	/**
	* 问题分析（考核得分情况分析）
	*/
	private String problemAnalysis;

	/**
	* 问题和改进措施（考核分析表（样本））
	*/
	private String improvement;

	/**
	* 分析说明
	*/
	private String analysisDescription;

	/**
	* 存在问题
	*/
	private String problem;

	/**
	* 改进措施
	*/
	private String measures;

	/**
	* 其他可用的协助持续改进的资源
	*/
	private String resources;

}