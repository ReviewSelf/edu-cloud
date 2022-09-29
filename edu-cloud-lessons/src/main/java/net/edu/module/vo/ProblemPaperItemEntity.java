package net.edu.module.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 问题卷关联问题表
 *
 * @author 樊磊
 * @since 1.0.0 2022-09-06
 */
@Data
@EqualsAndHashCode(callSuper=false)

public class ProblemPaperItemEntity {
    /**
     * id
     */

    private Long id;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 试卷id
     */
    private Long paperId;

    /**
     * 题目id
     */
    private Long problemId;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 题目名称
     */
    private String problemName;

    /**
     * 题目类型
     */
    private Integer problemType;

    /**
     * 类型
     */
    private Integer type;


    /**
     *来源
     */
    private Integer source;

    /**
     * 创建时间
     */
    private Date createTime;

}