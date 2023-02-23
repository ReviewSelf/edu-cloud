package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeachPayConvert;
import net.edu.module.dao.ClassHoursFlowRecordDao;
import net.edu.module.dao.TeachPayDao;
import net.edu.module.dao.UserDao;
import net.edu.module.entity.ClassHoursFlowRecordEntity;
import net.edu.module.entity.TeachPayEntity;
import net.edu.module.query.TeachPayQuery;
import net.edu.module.service.TeachPayService;
import net.edu.module.vo.TeachPayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 流水账管理
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-09-16
 */
@Service
@AllArgsConstructor
public class TeachPayServiceImpl extends BaseServiceImpl<TeachPayDao, TeachPayEntity> implements TeachPayService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ClassHoursFlowRecordDao classHoursFlowRecordDao;
    @Override
    public PageResult<TeachPayVO> page(TeachPayQuery query) {
        Page<TeachPayVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<TeachPayVO> list = baseMapper.page(page, query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    private LambdaQueryWrapper<TeachPayEntity> getWrapper(TeachPayQuery query){
        LambdaQueryWrapper<TeachPayEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(TeachPayVO vo) {
        TeachPayEntity entity = TeachPayConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
        //修改用户表销售状态为2，余课次增加，累计课次增加，累计金额增加
        userDao.updateUserAfterPay(vo.getUserId(),vo.getPayment(),vo.getBalance());
        //修改课次记录表
        ClassHoursFlowRecordEntity flowRecordEntity = new ClassHoursFlowRecordEntity();
        flowRecordEntity.setUserId(vo.getUserId());
        flowRecordEntity.setClassTimes(vo.getBalance());
        flowRecordEntity.setRemarks(vo.getBz());
        flowRecordEntity.setScene(4);
        flowRecordEntity.setStatus(2);
        classHoursFlowRecordDao.insert(flowRecordEntity);
    }

    @Override
    public void update(TeachPayVO vo) {
        TeachPayEntity entity = TeachPayConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public TeachPayVO getPaymentDetail(Long id) {
        return baseMapper.getPaymentDetail(id);
    }

}