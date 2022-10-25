package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;
import net.edu.framework.mybatis.entity.BaseEntity;


/**
 * target
 *
 * @author qxd babamu@126.com
 * @since 1.0.0 2022-10-24
 */
@EqualsAndHashCode(callSuper=false)
@Data
@TableName("archive_target")
public class ArchiveTargetEntity extends BaseEntity {

	/**
	* 指标点编号
	*/
	private Integer number;

	/**
	* 毕业要求编号
	*/
	private Long graduateId;

	/**
	* 名称
	*/
	private String name;

	/**
	* 描述
	*/
	private String content;







}
