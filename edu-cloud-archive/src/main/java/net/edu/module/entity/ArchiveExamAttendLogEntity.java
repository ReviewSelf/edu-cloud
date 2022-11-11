package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * 考核点
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-26
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("archive_exam")
public class ArchiveExamAttendLogEntity extends BaseEntity {
	/**
	* 考试编号
	*/
	@TableId
	private Long id;

	/**
	 * 考生id
	 */
	private Long userId;

	/**
	 * 考试id
	 */
	private String examId;



	/**
	 * 开始时间
	 */
	private Date joinTime;

	/**
	 * 结束时间
	 */
	private Date quitTime;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 得分
	 */
	private Integer score;

	/**
	 * 是否批改
	 */
	private Integer isCorrecting;


}
