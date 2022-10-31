package net.edu.mini.vo;

import lombok.Data;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年10月30日 17:07
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
