package net.edu.module.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.edu.framework.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
* target
*
* @author qxd babamu@126.com
* @since 1.0.0 2022-10-24
*/
@Data
@Schema(description = "target")
public class ArchiveTargetVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "编号")
	private Long id;

	@Schema(description = "毕业要求编号")
	private Long graduateId;

	@Schema(description = "名称")
	private String name;

	@Schema(description = "描述")
	private String content;


	@Schema(description = "版本号")
	private Integer version;

	@Schema(description = "修改人")
	private String updater;

	@Schema(description = "毕业要求名称")
	private String graduateTitle;

	@Schema(description = "年级")
	private String grade;

	@Schema(description = "排序")
	private Integer sort;

	@Schema(description = "毕业要求排序")
	private Integer graduateSort;


}
