package net.maku.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 记分册
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-22
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("archive_score_book")
public class ArchiveScoreBookEntity {
	/**
	* 编号
	*/
	@TableId
	private Long id;

	/**
	* 教学记事
	*/
	private String teachingNotes;

	/**
	* 授课班级名称
	*/
	private String className;

	/**
	* 教师
	*/
	private String teacherName;

	/**
	* 教研系
	*/
	private String majorName;

	/**
	* 课程表
	*/
	private String classSchedule;

	/**
	* 删除
	*/
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private Integer deleted;

	/**
	* 创建时间
	*/
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	/**
	* 修改时间
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	/**
	* 创建人
	*/
	@TableField(fill = FieldFill.INSERT)
	private String creator;

	/**
	* 版本号
	*/
	private Integer version;

	/**
	* 修改人
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String updater;

	/**
	 * 课程id
	 */
	private Long courseId;
}
