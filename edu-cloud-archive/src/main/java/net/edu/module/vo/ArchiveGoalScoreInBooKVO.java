package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年11月26日 21:08
 */
@Data
@Schema(description = "记分册成绩")
public class ArchiveGoalScoreInBooKVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String majorName;
    private String className;
    private String targetNum;
    private String actualNum;
    private String absentNum;
    private String excellent;
    private String excellentPercent;
    private String good;
    private String goodPercent;
    private String medium;
    private String mediumPercent;
    private String pass;
    private String passPercent;
    private String fail;
    private String failPercent;

}
