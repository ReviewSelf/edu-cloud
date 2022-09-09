package net.edu.module.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("choice_options")
public class ChoiceOptionEntity extends BaseEntity {

    private Long problemId;

    private String problemOption;

    private boolean isTrue;
}
