package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/11/4 9:23
 * @Version: 1.0
 * @Description:
 */
@Data
@Schema(description = "能力图")
public class AbilityMapVO implements Serializable {
    private static final long serialVersionUID = 1L;
    List<AbilityPointVO> abilityPointVOS;
    List<AbilityRelatedVO> abilityRelatedVOS;

}
