package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.module.dao.EnrollDao;
import net.edu.module.dao.PublicityDao;
import net.edu.module.entity.EnrollEntity;
import net.edu.module.entity.ReferenceEntity;
import net.edu.module.query.PublicityQuery;
import net.edu.module.service.PublicityService;
import net.edu.module.vo.ReferenceVO;
import net.edu.module.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class PublicityServiceImpl extends ServiceImpl<PublicityDao, ReferenceEntity> implements PublicityService {
    @Autowired
    PublicityDao publicityDao;

    @Override
    public PageResult<ReferenceVO> page(PublicityQuery query) {
        Page<ReferenceVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ReferenceVO> list = baseMapper.page(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }

    @Override
    public PageResult<UserVO> detail(PublicityQuery query) {
        Page<UserVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<UserVO> list = baseMapper.detail(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }
}
