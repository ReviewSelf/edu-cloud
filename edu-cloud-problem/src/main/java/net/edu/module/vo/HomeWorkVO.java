package net.edu.module.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年09月30日 17:07
 */
@Data
public class HomeWorkVO {
    String id;
    String lessonId;
    String problemId;
    String problemName;
    String problemType;
    String score;
    String beginTime;
    String endTime;
    String sort;
    String type;
}
