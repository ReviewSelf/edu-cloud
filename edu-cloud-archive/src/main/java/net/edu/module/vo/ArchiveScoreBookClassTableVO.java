package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年11月24日 17:36
 */
@Data
@Schema(description = "课程表")
public class ArchiveScoreBookClassTableVO implements Serializable {
    private static final long serialVersionUID = 1L;



    @Schema(description = "时间")
    private String time;

    @Schema(description = "地点")
    private String place;
}
