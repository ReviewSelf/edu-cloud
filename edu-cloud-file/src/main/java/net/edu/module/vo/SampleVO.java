package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 测试样例表
*
* @author sqw 
* @since 1.0.0 2022-09-07
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "测试样例表")
public class SampleVO implements Serializable {


	@Schema(description = "问题Id")
	private Long problemId;

	@Schema(description = "样例输入文件地址")
	private String inputPath;

	@Schema(description = "样例输出文件地址")
	private String outputPath;

	@Schema(description = "样例输入文件大小")
	private Long inputSize;

	@Schema(description = "样例输出文件大小")
	private Long outputSize;





}