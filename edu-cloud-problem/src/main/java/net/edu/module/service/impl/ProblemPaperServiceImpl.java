package net.edu.module.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ProblemPaperConvert;
import net.edu.module.dao.ProblemPaperDao;
import net.edu.module.entity.ProblemPaperEntity;
import net.edu.module.entity.ProblemPaperItemEntity;
import net.edu.module.query.ProblemPaperQuery;
import net.edu.module.service.ProblemPaperService;
import net.edu.module.vo.CodeProblemVO;
import net.edu.module.vo.ProblemPaperVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 问题卷表
 *
 * @author 樊磊 
 * @since 1.0.0 2022-09-05
 */
@Service
@AllArgsConstructor
public class ProblemPaperServiceImpl extends BaseServiceImpl<ProblemPaperDao, ProblemPaperEntity> implements ProblemPaperService {

    @Override
    public PageResult<ProblemPaperVO> page(ProblemPaperQuery query) {
        Page<ProblemPaperVO> page = new Page<>(query.getPage(),query.getLimit());
        IPage<ProblemPaperVO> list = baseMapper.page(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }




    @Override
    public void save(ProblemPaperVO vo) {
        ProblemPaperEntity entity = ProblemPaperConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ProblemPaperVO vo) {
        ProblemPaperEntity entity = ProblemPaperConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public void updateStatus(Long paperId) {
        baseMapper.updateStatus(paperId);
    }

    @Override
    public void updateNumAndScore(List<ProblemPaperItemEntity> paperItemList) {
        int totalScore = 0;
        for (int i=0 ; i < paperItemList.size();i++){
            totalScore += paperItemList.get(i).getScore();
        }
        baseMapper.updateNumAndScore(paperItemList.get(0).getPaperId(),paperItemList.size(),totalScore);


    }


}