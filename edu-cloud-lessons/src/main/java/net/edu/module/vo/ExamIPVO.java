package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
* 1
*
* @author 马佳浩 
* @since 1.0.0 2022-09-15
*/
@Data
@Schema(description = "1")
public class ExamIPVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@Schema(description = "课堂id")
	private Long examId;

	@Schema(description = "IP段")
	private String ipRange;

	@Schema(description = "备注")
	private String remark;

}