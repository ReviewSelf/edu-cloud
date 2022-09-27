package net.edu.module.service;


import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ProblemPaperItemEntity;

import java.util.List;

/**
 * 问题卷关联问题表
 *
 * @author 樊磊 
 * @since 1.0.0 2022-09-06
 */
public interface ProblemPaperItemService extends BaseService<ProblemPaperItemEntity> {


    List<ProblemPaperItemEntity> get(Long paperId);
    void insert(List<ProblemPaperItemEntity> list);


    void delete(Long paperId);
}