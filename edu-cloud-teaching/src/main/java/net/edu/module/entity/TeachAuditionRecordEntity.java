package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;

import java.util.Date;

/**
 * 教学试听安排
 *
 * @author sqw 
 * @since 1.0.0 2023-02-13
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_audition_record")
public class TeachAuditionRecordEntity extends BaseEntity {
	private Long id;
	/**
	* 学生id
	*/
	private Long studentId;


	/**
	* 班级id
	*/
	private Long lessonId;


	/**
	* 安排时间
	*/
	private Date dateTime;

	/**
	 * 试听备注
	 */
	private String remarks;

	/**
	* 是否已安排试听,0未安排，1安排
	*/
	private Integer status;

}