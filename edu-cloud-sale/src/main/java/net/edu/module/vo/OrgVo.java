package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.TreeNode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "机构名称表")
public class OrgVo extends TreeNode<OrgVo> {

    @Schema(description = "机构名称")
    @NotBlank(message = "机构名称不能为空")
    private String name;

    @Schema(description = "机构ID")
    private Long id;

    @Schema(description = "机构父名称")
    private String parentName;

    @Schema(description = "排序")
    @Min(value = 0 , message = "排序值不能小与0")
    private Integer sort;

}
