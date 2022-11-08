package net.edu.module.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/11/8 9:16
 * @Version: 1.0
 * @Description:
 */
@Data
public class AbilityListVO {

    private Long id;

    private String name;


    private Integer level;

    List<AbilityListVO> children;
}
