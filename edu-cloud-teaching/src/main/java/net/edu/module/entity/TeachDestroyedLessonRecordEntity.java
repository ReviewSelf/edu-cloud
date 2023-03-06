package net.edu.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.edu.framework.mybatis.entity.BaseEntity;
import org.apache.poi.hpsf.Decimal;

/**
 * 消课管理
 *
 * @author sqw 
 * @since 1.0.0 2023-03-04
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("teach_destroyed_lesson_record")
public class TeachDestroyedLessonRecordEntity extends BaseEntity {
	/**
	* 学生id
	*/
	private Long stuId;

	/**
	* 班级id
	*/
	private Long classId;

	/**
	* 课堂id
	*/
	private Long lessonId;

	/**
	* 状态1=已扣费0=未扣费
	*/
	private Integer status;

	/**
	* 时长
	*/
	private Decimal duration;

	/**
	* 实际扣时
	*/
	private Decimal actualDeduction;

	/**
	 * 说明
	 */
	private String direction;

	/**
	* 类型0=常规课1=集训课
	*/
	private Integer type;

}