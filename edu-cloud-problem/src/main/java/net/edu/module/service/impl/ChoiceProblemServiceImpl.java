package net.edu.module.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ChoiceProblemConvert;
import net.edu.module.entity.ChoiceProblemEntity;
import net.edu.module.entity.CodeProblemEntity;
import net.edu.module.entity.FillProblemEntity;
import net.edu.module.query.ChoiceProblemQuery;
import net.edu.module.vo.ChoiceOptionVO;
import net.edu.module.vo.ChoiceProblemVO;
import net.edu.module.dao.ChoiceProblemDao;
import net.edu.module.service.ChoiceProblemService;
import net.edu.module.vo.CodeProblemVO;
import net.edu.module.vo.FillProblemVO;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 选择题库表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-05
 */
@Service
@AllArgsConstructor
public class ChoiceProblemServiceImpl extends BaseServiceImpl<ChoiceProblemDao, ChoiceProblemEntity> implements ChoiceProblemService {

    private final ChoiceProblemDao choiceProblemDao;

    @Override
    public PageResult<ChoiceProblemVO> page(ChoiceProblemQuery query) {
        Page<ChoiceProblemVO> page = new Page<>(query.getPage(),query.getLimit());
        IPage<ChoiceProblemVO> list = choiceProblemDao.page(page,query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }



    @Override
    public void save(ChoiceProblemVO vo) {
        ChoiceProblemEntity entity = ChoiceProblemConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ChoiceProblemVO vo) {
        ChoiceProblemEntity entity = ChoiceProblemConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public boolean updateStatus(Integer id) {
        choiceProblemDao.updateStatus(id);
        return true;
    }

    @Override
    public List<ChoiceOptionVO> getOption(Integer id) {
        return choiceProblemDao.selectOption(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOption(List<ChoiceOptionVO> list) {
        choiceProblemDao.deleteOption(list.get(0).getProblemId());
        for (int i=0;i<list.size();i++) {
            choiceProblemDao.insertOption(list.get(i));
        }
        choiceProblemDao.updateOptionNum(list.get(0).getProblemId());
    }

}