package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ChoiceProblemConvert;
import net.edu.module.entity.ChoiceProblemEntity;
import net.edu.module.query.ChoiceProblemQuery;
import net.edu.module.vo.ChoiceOptionVO;
import net.edu.module.vo.ChoiceProblemVO;
import net.edu.module.dao.ChoiceProblemDao;
import net.edu.module.service.ChoiceProblemService;
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
    public ChoiceProblemVO getChoiceProblem(Long problemId) {
        return choiceProblemDao.selectChoiceProblem(problemId);
    }
    @Override
    @Transactional
    public void save(ChoiceProblemVO vo) {
        ChoiceProblemEntity entity = ChoiceProblemConvert.INSTANCE.convert(vo);
        entity.setOptionNum(vo.getOptions().size());
        baseMapper.insert(entity);
        if(vo.getOptions().size()>0){
            choiceProblemDao.insertOption(vo.getOptions(),entity.getId());
        }
        System.out.println(entity.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ChoiceProblemVO vo) {
        ChoiceProblemEntity entity = ChoiceProblemConvert.INSTANCE.convert(vo);
        entity.setOptionNum(vo.getOptions().size());
        //删除原先选项
        choiceProblemDao.deleteOption(entity.getId());
        if(vo.getOptions().size()>0){
            choiceProblemDao.insertOption(vo.getOptions(),entity.getId());
        }
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public void updateStatus(Long problemId) {
        choiceProblemDao.updateStatus(problemId);
    }

    @Override
    public Boolean updateUsedNum(Long id) {
        int ans = choiceProblemDao.updateUsedNum(id);
        if(ans!=0) return true;
        return false;
    }

    @Override
    public Boolean updateSubmitTimes(Long id, Boolean isTrue) {
        int ans =  choiceProblemDao.updateSubmitTimes(id,isTrue);
        if(ans!=0) return true;
        return false;
    }


}