package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * 能力纬度图
 *
 * @author sqw 
 * @since 1.0.0 2022-10-27
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("ability")
public class AbilityEntity extends BaseEntity {


	/**
	 * pid
	 */
	private Long pid;

	/**
	* 能力图名称
	*/
	private String name;

	/**
	* 顺序
	*/
	private Integer level;


}