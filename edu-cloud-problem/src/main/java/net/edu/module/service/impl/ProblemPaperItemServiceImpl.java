package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.edu.framework.common.constant.Constant;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ProblemPaperItemConvert;
import net.edu.module.entity.ProblemPaperItemEntity;
import net.edu.module.query.ProblemPaperItemQuery;
import net.edu.module.service.ProblemPaperService;
import net.edu.module.vo.ProblemPaperItemVO;
import net.edu.module.dao.ProblemPaperItemDao;
import net.edu.module.service.ProblemPaperItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 问题卷关联问题表
 *
 * @author 樊磊 
 * @since 1.0.0 2022-09-06
 */
@Service
@AllArgsConstructor
public class ProblemPaperItemServiceImpl extends BaseServiceImpl<ProblemPaperItemDao, ProblemPaperItemEntity> implements ProblemPaperItemService {



    private final ProblemPaperService problemPaperService;

    @Override
    public List<ProblemPaperItemEntity> get(Long paperId) {

        return baseMapper.selectPageRecords(paperId);
    }

    @Override
    public void insert(List<ProblemPaperItemEntity> list) {
        //插入新的试卷题目前先删除老的题目
        delete(list.get(0).getPaperId());
        //插入新题目
        baseMapper.insert(list);
        //更新对应试卷的题目数量和总分数
        problemPaperService.updateNumAndScore(list);

    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long paperId) {
        baseMapper.delete(paperId);
    }


}