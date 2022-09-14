package net.edu.module.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.FillProblemConvert;
import net.edu.module.entity.ChoiceProblemEntity;
import net.edu.module.entity.FillProblemEntity;
import net.edu.module.query.FillProblemQuery;
import net.edu.module.vo.ChoiceProblemVO;
import net.edu.module.vo.CodeProblemVO;
import net.edu.module.vo.FillProblemVO;
import net.edu.module.dao.FillProblemDao;
import net.edu.module.service.FillProblemService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 填空题表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-05
 */
@Service
@AllArgsConstructor
public class FillProblemServiceImpl extends BaseServiceImpl<FillProblemDao, FillProblemEntity> implements FillProblemService {

    private final FillProblemDao fillProblemDao;

    @Override
    public PageResult<FillProblemVO> page(FillProblemQuery query) {
        Page<FillProblemVO> page = new Page<>(query.getPage(),query.getLimit());
            IPage<FillProblemVO> list = fillProblemDao.page(page,query);
            return new PageResult<>(list.getRecords(), list.getTotal());
    }

    private LambdaQueryWrapper<FillProblemEntity> getWrapper(FillProblemQuery query){
        LambdaQueryWrapper<FillProblemEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotBlank(query.getName()), FillProblemEntity::getName, query.getName());
        wrapper.eq(query.getStatus() != null, FillProblemEntity::getStatus, query.getStatus());
        wrapper.eq(query.getDifficulty() != null, FillProblemEntity::getDifficulty, query.getDifficulty());
        wrapper.orderByAsc(FillProblemEntity::getUpdateTime);
        return wrapper;
    }

    @Override
    public void save(FillProblemVO vo) {
        FillProblemEntity entity = FillProblemConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(FillProblemVO vo) {
        FillProblemEntity entity = FillProblemConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {

        removeByIds(idList);
    }

    @Override
    public void updateStatus(Long problemId) {
        fillProblemDao.updateStatus(problemId);
    }


    @Override
    public void updateUsedNum(Long id) {
        fillProblemDao.updateUsedNum(id);

    }

    @Override
    public void updateSubmitTimes(Long id, Boolean isTrue) {
        fillProblemDao.updateSubmitTimes(id,isTrue);

    }


}