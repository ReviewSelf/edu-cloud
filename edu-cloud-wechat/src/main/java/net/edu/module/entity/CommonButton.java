package net.edu.module.entity;

import lombok.Data;

/**
 * @author weng
 * 子菜单
 * @date 2022/9/24 - 11:31
 **/
@Data
public class CommonButton extends Button{
    /**
     * 按钮类型
     */
    private String type;

    /**
     * 按钮的key
     */
    private String key;

    /**
     * 按钮的路由
     */
    private String url;
}