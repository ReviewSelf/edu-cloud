package net.edu.module.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.framework.common.utils.TreeNode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


/**
 * @Author: 马佳浩
 * @Date: 2022/9/6 13:09
 * @Version: 1.0
 * @Description:
 */
@Data
@Schema(description = "知识点表")
public class KnowledgePointVO extends TreeNode<KnowledgePointVO> {

    @Schema(description = "知识点名称")
    @NotBlank(message = "知识点名称不能为空")
    private String name;

    @Schema(description = "知识点说明")
    private String description;

    @Schema(description = "代码")
    private String code;

    private Integer level;

    @Schema(description = "排序")
    @Min(value = 0, message = "排序值不能小于0")
    private Integer sort;

    @Schema(description = "父名称")
    private String parentName;
}
