package net.edu.module.vo;

import lombok.Data;

/**
 * @Author: 马佳浩
 * @Date: 2022/11/7 10:03
 * @Version: 1.0
 * @Description:
 */
@Data
public class KpProblemVO {
   private Long id;
   private String name;
   private Integer difficulty;
   private Integer problemType;
}
