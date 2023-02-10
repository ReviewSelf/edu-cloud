package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.edu.framework.common.page.PageResult;
import net.edu.module.dao.CommunicateDao;
import net.edu.module.entity.CommunicateEntity;
import net.edu.module.query.CommunicateQuery;
import net.edu.module.service.CommunicateService;
import net.edu.module.vo.CommunicateVO;
import net.edu.module.vo.EnrollVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weng
 * @date 2023/2/5 - 10:41
 **/
@Service
public class CommunicateServiceImpl extends ServiceImpl<CommunicateDao,CommunicateEntity> implements CommunicateService {

    @Autowired
    private CommunicateDao communicateDao;
    @Override
    public void update(CommunicateVO vo) {

    }

    @Override
    public PageResult<CommunicateVO> page(CommunicateQuery query) {
        Page<CommunicateVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<CommunicateVO> list = communicateDao.page(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }
}