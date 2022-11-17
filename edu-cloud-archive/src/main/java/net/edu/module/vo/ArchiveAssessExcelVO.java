package net.edu.module.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年11月15日 22:11
 */
@Data
@Schema(description = "考核点")
public class ArchiveAssessExcelVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ExcelIgnore
    @Schema(description = "id")
    private Long id;

    @Schema(description = "学号", required = true)
    @NotBlank(message = "学号不能为空")
    @ExcelProperty(index = 0)
    private String stuId;

    @Schema(description = "姓名", required = true)
    @NotBlank(message = "姓名不能为空")
    @ExcelProperty(index = 1)
    private String stuName;

    @Schema(description = "考核点1")
    @ExcelProperty(index = 2)
    private String KHD1;
}
