package net.edu.module.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_plan_item_resource")
public class TeachPlanItemResourceEntity extends BaseEntity {

    /**
     * 日历资源名称
     */
    private String name;


    private Long itemId;
    /**
     * 日历资源路径
     */
    private String filePath;
    /**
     * 日历资源备注
     */
    private String remark;
    /**
     * 日历资源类型
     */
    private Integer fileType;

}
