package net.edu.module.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.CodeProblemConvert;
import net.edu.module.entity.CodeProblemEntity;
import net.edu.module.entity.FillProblemEntity;
import net.edu.module.query.CodeProblemQuery;
import net.edu.module.vo.CodeProblemVO;
import net.edu.module.dao.CodeProblemDao;
import net.edu.module.service.CodeProblemService;
import org.mapstruct.ap.internal.model.assignment.UpdateWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 代码题库表
 *
 * @author 马佳浩 
 * @since 1.0.0 2022-09-05
 */
@Service
@AllArgsConstructor
public class CodeProblemServiceImpl extends BaseServiceImpl<CodeProblemDao, CodeProblemEntity> implements CodeProblemService {

    private final CodeProblemDao codeProblemDao;

    @Override
    public PageResult<CodeProblemVO> page(CodeProblemQuery query) {
        Page<CodeProblemVO> page = new Page<>(query.getPage(),query.getLimit());
        IPage<CodeProblemVO> list = codeProblemDao.page(page,query);

        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    private LambdaQueryWrapper<CodeProblemEntity> getWrapper(CodeProblemQuery query){
        LambdaQueryWrapper<CodeProblemEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotBlank(query.getName()), CodeProblemEntity::getName, query.getName());
        wrapper.eq(query.getStatus() != null, CodeProblemEntity::getStatus, query.getStatus());
        wrapper.eq(query.getDifficulty() != null, CodeProblemEntity::getDifficulty, query.getDifficulty());
        wrapper.orderByAsc(CodeProblemEntity::getUpdateTime);
        return wrapper;
    }

    @Override
    public void save(CodeProblemVO vo) {
        CodeProblemEntity entity = CodeProblemConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(CodeProblemVO vo) {
        CodeProblemEntity entity = CodeProblemConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public boolean updateStatus(Integer id) {
        codeProblemDao.updateStatus(id);
        return true;
    }

}