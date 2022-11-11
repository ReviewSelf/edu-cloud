package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * 考核点
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-26
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("archive_exam")
public class ArchiveExamEntity extends BaseEntity {
	/**
	* 考试编号
	*/
	@TableId
	private Long id;

	/**
	 * 试卷编号
	 */
	private Long paperId;

	/**
	 * 考试名称
	 */
	private String name;

	/**
	 * 试卷说明
	 */
	private String description;

	/**
	 * 开始时间
	 */
	private Date beginTime;

	/**
	 * 结束时间
	 */
	private Date endTime;

	/**
	 * 总分
	 */
	private Integer score;

	/**
	 * 监考老师
	 */
	private Long teacherId;

	/**
	 * 知识点id
	 */
	private String kpCode;

	/**
	 * 考试地点
	 */
	private String place;

	/**
	 * 考试时长
	 */
	private Integer timeLimit;

	/**
	 * 班级id
	 */
	private Long classId;

	/**
	 * 题目数量
	 */
	private Integer problemNum;
}
