package net.edu.module.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import net.edu.framework.common.utils.DateUtils;

import java.util.Date;

/**
 * 班级表
 *
 * @author wengruichen babamu@126.com
 * @since 1.0.0 2022-09-09
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_class")
public class TeachClassEntity {
	/**
	 * 班级ID
	 */
	@TableId
	private Long id;

	/**
	 * 班级名称
	 */
	private String name;

	/**
	 * 班级介绍
	 */
	private String description;

	/**
	 * 班级封面
	 */
	private String img;

	/**
	 * 引用教学计划
	 */
	private Long planId;

	/**
	 * 总课时
	 */
	private Integer lessonNum;

	/**
	 * 周频,0则表示无
	 */
	private Integer frequency;

	/**
	 * 班级状态，0=未发布，1=未开班，2=开班中，3=结班
	 */
	private Integer status;


	/**
	 * 上课开始时间
	 */
	private String lessonBeginTime;

	/**
	 * 上课结束时间
	 */
	private String lessonEndTime;

	/**
	 * 班级预计开始时间
	 */
	private String beginTime;

	/**
	 * 班级预计结束时间
	 */
	private String endTime;

	/**
	 * 班主任
	 */
	private Integer teacher1Id;

	/**
	 * 任课老师
	 */
	private Integer teacher2Id;

	/**
	 * 下一次课程id
	 */
	private Integer nextLessson;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	/**
	 * 逻辑删除
	 */
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private Integer deleted;

	/**
	 * 更新人
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updater;

	/**
	 * 创建人
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long creator;

	/**
	 * 版本
	 */
	private Integer version;

	/**
	 * 上课地点
	 */
	private String place;

}
