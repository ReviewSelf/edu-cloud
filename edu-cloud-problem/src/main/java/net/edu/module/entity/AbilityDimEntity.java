package net.edu.module.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * 能力模块表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-25
 */

@Data
@TableName("ability_dim")
public class AbilityDimEntity {
	/**
	* 自增主键
	*/
	@TableId
	private Long id;

	/**
	* 上级id，一级菜单为0
	*/
	private Long pid;

	/**
	* 能力板块名称
	*/
	private String name;

	/**
	* 层级
	*/
	private Integer level;

	/**
	* 达标个数
	*/
	private Integer standardNum;

	/**
	* 是否删除
	*/
	private Integer deleted;

	/**
	* 创建者
	*/
	private Long creator;

	/**
	* 创建时间
	*/
	private Date createTime;

	/**
	* 更新者
	*/
	private Long updater;

	/**
	* 更新时间
	*/
	private Date updateTime;

}