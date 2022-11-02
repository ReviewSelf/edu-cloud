package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* 能力纬度图
*
* @author sqw 
* @since 1.0.0 2022-10-27
*/
@Data
@Schema(description = "能力纬度点")
public class AbilityPointVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "自增主键")
	private Long id;

	@Schema(description = "能力图主键")
	private Long abilityId;

	@Schema(description = "知识点代码")
	private String code;

	@Schema(description = "知识点名称")
	private String kpName;

	@Schema(description = "指标1")
	private Integer lv1Num;

	@Schema(description = "指标2")
	private Integer lv2Num;

	@Schema(description = "指标3")
	private Integer lv3Num;

	@Schema(description = "指标4")
	private Integer lv4Num;

	@Schema(description = "指标5")
	private Integer lv5Num;

	@Schema(description = "横坐标")
	private Integer coordinateX;

	@Schema(description = "纵坐标")
	private Integer coordinateY;

}