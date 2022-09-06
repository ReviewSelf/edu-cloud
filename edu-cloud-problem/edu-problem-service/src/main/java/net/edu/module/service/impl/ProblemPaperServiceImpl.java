package net.edu.module.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import lombok.AllArgsConstructor;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ProblemPaperConvert;
import net.edu.module.dao.ProblemPaperDao;
import net.edu.module.entity.ProblemPaperEntity;
import net.edu.module.query.ProblemPaperQuery;
import net.edu.module.service.ProblemPaperService;
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
        IPage<ProblemPaperEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(ProblemPaperConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<ProblemPaperEntity> getWrapper(ProblemPaperQuery query){
        LambdaQueryWrapper<ProblemPaperEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotBlank(query.getName()), ProblemPaperEntity::getName, query.getName());
        wrapper.eq(query.getStatus() != null, ProblemPaperEntity::getStatus, query.getStatus());
        wrapper.eq(query.getDifficulty()!= null, ProblemPaperEntity::getDifficulty, query.getDifficulty());
        return wrapper;
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

}