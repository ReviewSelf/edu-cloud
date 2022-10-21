package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.WxMsgLogDao;
import net.edu.module.entity.WxMsgLogEntity;
import net.edu.module.query.WxMsgLogQuery;
import net.edu.module.service.WxMsgLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 消息推送
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-11
 */
@Service
@AllArgsConstructor
public class WxMsgLogServiceImpl extends BaseServiceImpl<WxMsgLogDao, WxMsgLogEntity> implements WxMsgLogService {

    @Autowired
    WxMsgLogDao wxMsgLogDao;
    @Override
    public PageResult<WxMsgLogEntity> page(WxMsgLogQuery query) {

        Page<WxMsgLogEntity> page = new Page<>(query.getPage(), query.getLimit());
        IPage<WxMsgLogEntity> list =wxMsgLogDao.selectWxMsgLogByPage(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }

    @Override
    public WxMsgLogEntity selectById(Long id) {
        return wxMsgLogDao.selectWxMsgLogById(id);
    }

    private LambdaQueryWrapper<WxMsgLogEntity> getWrapper(WxMsgLogQuery query){
        LambdaQueryWrapper<WxMsgLogEntity> wrapper = Wrappers.lambdaQuery();
        return wrapper;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(List<Long> idList) {
        return wxMsgLogDao.deleteWxMsgLogByIds(idList);
    }

}
