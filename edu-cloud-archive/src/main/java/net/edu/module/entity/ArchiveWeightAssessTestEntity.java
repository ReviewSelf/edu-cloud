package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

/**
 * @author weng
 * @date 2022/11/3 - 11:37
 **/
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("archive_weight_target_assess")
public class ArchiveWeightAssessTestEntity extends BaseEntity {

    /**
     * 编号
     */
    private Long id;

    /**
     * 评测点编号
     */
    private Long testId;

    /**
     * 考核点编号
     */
    private Long assessId;

    /**
     * 支撑度（百分比）
     */
    private Double weight;

    /**
     * 来源
     */
    private String source;
}