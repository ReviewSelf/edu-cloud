package net.edu.module.entity;

import lombok.Data;

/**
 * @author weng
 * @date 2022/9/24 - 11:32
 **/
//父菜单
@Data
public class ComplexButton extends Button{
    private Button [] sub_button;
}