package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

/**
 * 测试样例表
 *
 * @author sqw 
 * @since 1.0.0 2022-09-07
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("problem_code_sample")
public class CodeSampleEntity extends BaseEntity {

	/**
	 * 问题ID
	 */
	private Long problemId;

	/**
	* 样例输入文件地址
	*/
	private String inputPath;

	/**
	* 样例输出文件地址
	*/
	private String outputPath;

	/**
	 * 样例输入文件大小
	 */
	private Long inputSize;

	/**
	 * 样例输出文件大小
	 */
	private Long outputSize;


}