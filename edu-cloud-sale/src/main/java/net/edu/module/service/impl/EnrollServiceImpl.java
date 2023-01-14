package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.module.convert.EnrollConvert;
import net.edu.module.dao.EnrollDao;
import net.edu.module.entity.EnrollEntity;
import net.edu.module.query.EnrollQuery;
import net.edu.module.service.EnrollService;
import net.edu.module.vo.EnrollVO;
import net.edu.module.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weng
 * @date 2023/1/13 - 13:20
 **/
@Service
@AllArgsConstructor
public class EnrollServiceImpl extends ServiceImpl<EnrollDao, EnrollEntity> implements EnrollService {
    @Autowired
    private EnrollDao enrollDao;
    @Override
    public PageResult<EnrollVO> page(EnrollQuery query) {
        Page<EnrollVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<EnrollVO> list = enrollDao.selectEnrollByPage(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }



    @Override
    public void update(EnrollVO vo) {
        EnrollEntity entity = EnrollConvert.INSTANCE.convert(vo);

        updateById(entity);
    }




}