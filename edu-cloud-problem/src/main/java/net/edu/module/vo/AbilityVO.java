package net.edu.module.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;
import net.edu.framework.common.utils.TreeNode;

import java.io.Serializable;
import java.util.Date;

/**
* 能力纬度图
*
* @author sqw 
* @since 1.0.0 2022-10-27
*/
@Data
@Schema(description = "能力纬度图")
public class AbilityVO extends TreeNode<AbilityVO> implements Serializable {
	private static final long serialVersionUID = 1L;


	@Schema(description = "能力图名称")
	private String name;

	@Schema(description = "顺序")
	private Integer level;

	@Schema(description = "是否删除")
	private Integer deleted;

	private String parentName;

	@Schema(description = "创建时间")
	@JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
	private Date createTime;

	private	Integer judgeUnlock;


}