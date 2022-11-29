package net.edu.module.vo;

import lombok.Data;

import java.util.List;

@Data
public class AbilityUserVo {
    private Long abilityId;

    private List<AbilityVO> childAbilities;
}
