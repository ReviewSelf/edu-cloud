package net.edu.module.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "能力点批量导入")
public class AbilityBatchImportVO {
    private Long id;
    private List<String> list;
}
