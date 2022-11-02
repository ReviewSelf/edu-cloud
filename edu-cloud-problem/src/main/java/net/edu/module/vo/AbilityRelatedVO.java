package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
* 能力纬度图
*
* @author sqw 
* @since 1.0.0 2022-10-27
*/
@Data
@Schema(description = "能力纬度点关系")
public class AbilityRelatedVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "自增主键")
	private Long id;

	@Schema(description = "能力图主键")
	private Long abilityId;

	@Schema(description = "来源知识点代码")
	private String sourceCode;

	@Schema(description = "来源")
	private String source;

	@Schema(description = "去向知识点代码")
	private String targetCode;

	@Schema(description = "去向")
	private String target;

}