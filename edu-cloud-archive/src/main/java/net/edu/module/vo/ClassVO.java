package net.edu.module.vo;


import lombok.Data;
import java.io.Serializable;


/**
* 班级
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-25
*/
@Data
public class ClassVO implements Serializable {
	private Long  id;

	private String className;
}