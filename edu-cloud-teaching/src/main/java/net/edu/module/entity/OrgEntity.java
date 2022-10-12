package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

/**
 * @Author: ykb
 * @Date: 2022/10/9 13:10
 * @Version: 1.0
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sys_org")
public class OrgEntity extends BaseEntity {

    /**
     * ID
     */
    private Long id;
    /**
     * 上级ID，一级菜单为0
     */
    private Long pid;
    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

}
