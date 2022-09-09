package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
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
@Schema(description = "测试样例表")
public class CodeSampleVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long problemId;

	@Schema(description = "样例输入文件地址")
	private String inputPath;

	@Schema(description = "样例输出文件地址")
	private String outputPath;

	@Schema(description = "样例输入文件大小")
	private String inputSize;

	@Schema(description = "样例输出文件大小")
	private String outputSize;

	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;



}