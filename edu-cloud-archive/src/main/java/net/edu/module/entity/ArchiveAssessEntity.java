package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 考核点
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-26
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("archive_assess")
public class ArchiveAssessEntity {
	/**
	* 考核点编号
	*/
	@TableId
	private Long id;

	/**
	* 考核点名称
	*/
	private String name;

//	/**
//	* 年级
//	*/
//	private String grade;

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

}
