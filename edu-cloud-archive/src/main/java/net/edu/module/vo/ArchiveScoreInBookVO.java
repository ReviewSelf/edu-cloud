package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import net.edu.module.entity.ArchiveAssessEntity;
import net.edu.module.entity.ArchiveWeightAssessTestEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年11月27日 16:12
 */
@Data
@Schema(description = "记分册分数记录")
public class ArchiveScoreInBookVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String stuId;
    private String stuName;
    private List<ArchiveTestNameToGoal> testList;
    private List<ArchiveAssessNameToGoal> assessList;
}
