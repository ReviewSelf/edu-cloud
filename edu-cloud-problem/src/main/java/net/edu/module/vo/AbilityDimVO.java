package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import net.edu.framework.common.utils.DateUtils;
import net.edu.framework.common.utils.TreeNode;

import java.util.Date;

/**
* 能力模块表
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-25
*/
@Data
@Schema(description = "能力模块表")
public class AbilityDimVO extends TreeNode<KnowledgePointVO> {

	@Schema(description = "自增主键")
	private Long id;

	@Schema(description = "上级id，一级菜单为0")
	private Long pid;

	@Schema(description = "能力板块名称")
	private String name;

	@Schema(description = "层级")
	private Integer level;

	@Schema(description = "达标个数")
	private Integer standardNum;

	@Schema(description = "父节点名称")
	private String parentName;
}