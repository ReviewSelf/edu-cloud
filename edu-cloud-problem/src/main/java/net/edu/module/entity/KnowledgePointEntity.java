package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/6 13:10
 * @Version: 1.0
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("problem_knowledge_point")
public class KnowledgePointEntity extends BaseEntity {
    /**
     * 上级ID，一级菜单为0
     */
    private Long pid;
    /**
     * 知识点名称
     */
    private String name;

    /**
     * 代码
     */
    private String code;
    /**
     * 知识点说明
     */
    private String description;

    /**
     * 知识点说明
     */
    private Integer sort;

}
