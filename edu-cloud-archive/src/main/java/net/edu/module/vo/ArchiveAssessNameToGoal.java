package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年11月27日 16:33
 */
@Data
@Schema(description = "记分册总结记录")
public class ArchiveAssessNameToGoal implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String score;
}
