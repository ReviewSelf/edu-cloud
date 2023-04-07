package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.api.EduTeachApi;
import net.edu.module.convert.TeachPayConvert;
import net.edu.module.dao.TeachPayDao;
import net.edu.module.dao.UserDao;
import net.edu.module.entity.TeachPayEntity;
import net.edu.module.query.TeachPayQuery;
import net.edu.module.service.TeachPayService;
import net.edu.module.vo.StatisticsTeacherVO;
import net.edu.module.vo.TeachClassHoursFlowRecordVO;
import net.edu.module.vo.TeachPayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
    private EduTeachApi eduTeachApi;
    @Override
    public PageResult<TeachPayVO> page(TeachPayQuery query) {
        Page<TeachPayVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<TeachPayVO> list = baseMapper.page(page, query);
        for (TeachPayVO record : list.getRecords()) {
            if (record.getPayable().compareTo(new BigDecimal(0)) > 0) record.setIsPay(1);
            else  record.setIsPay(0);
            record.setPayable(record.getPayable().abs());
        }
        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    private LambdaQueryWrapper<TeachPayEntity> getWrapper(TeachPayQuery query){
        LambdaQueryWrapper<TeachPayEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(TeachPayVO vo) {
        TeachPayEntity entity = TeachPayConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
        //修改用户表销售状态为2，余课次增加，累计课次增加，累计金额增加
        userDao.updateUserAfterPay(vo);
        //修改课次记录表
        TeachClassHoursFlowRecordVO recordVO = new TeachClassHoursFlowRecordVO();
        recordVO.setUserId(vo.getUserId());
        if(vo.getClassType() == 0){
            recordVO.setNormal(vo.getClassHours());
        }
        else {
            recordVO.setTraining(vo.getClassHours());
        }
        if(vo.getPresentType() == 0){
            recordVO.setNormalPresent(vo.getPresentHours());
        }
        else {
            recordVO.setTrainingPresent(vo.getPresentHours());
        }
        recordVO.setType(1);
        recordVO.setDirection(vo.getBz());
        eduTeachApi.insertClassHoursFlowRecord(recordVO);

    }

    @Override
    public void update(TeachPayVO vo) {
        TeachPayEntity entity = TeachPayConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public TeachPayVO getPaymentDetail(Long id) {
        TeachPayVO paymentDetail = baseMapper.getPaymentDetail(id);
        paymentDetail.setPayment(paymentDetail.getPayment().abs());
        return paymentDetail;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void returnBack(TeachPayVO vo) {
        vo.setPayment(vo.getPayment().negate());
        TeachPayEntity entity = TeachPayConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
        userDao.returnBack(vo);
        TeachClassHoursFlowRecordVO recordVO = new TeachClassHoursFlowRecordVO();
        recordVO.setUserId(vo.getUserId());
        if(vo.getClassType() == 0){
            recordVO.setNormal(vo.getClassHours());
        }
        else {
            recordVO.setTraining(vo.getClassHours());
        }
        recordVO.setType(0);
        recordVO.setDirection(vo.getBz());
        eduTeachApi.insertClassHoursFlowRecord(recordVO);
    }

    @Override
    public List<HashMap<String, String>> statisticsAmount() {
        return baseMapper.statisticsAmount();
    }

    @Override
    public List<HashMap<String, String>> statisticsPeople() {
        return baseMapper.statisticsPeople();
    }

    @Override
    public List<Long> statisticsMonth(){
        List<Long> list = new ArrayList<>();
        list.add(baseMapper.statisticsNewPeopleThisMonth());
        list.add(baseMapper.statisticsNewCommunicateThisMonth());
        list.add(baseMapper.statisticsNewAuditionThisMonth());
        list.add(baseMapper.statisticsNewDealThisMonth());
        list.add(baseMapper.statisticsTotalThisMonth());
        Long res = (baseMapper.statisticsTotalThisMonth()-baseMapper.statisticsTotalLastMonth())*100/baseMapper.statisticsTotalLastMonth();
        list.add(res);
        return list;
    }
    @Override
    public StatisticsTeacherVO statisticsTeacher(Long id,int season){
        String month1 = null,month2 = null,month3 = null;
        if (season==1){
            month1 = "1";
            month2 = "2";
            month3 = "3";
        }else if (season==2){
            month1 = "4";
            month2 = "5";
            month3 = "6";
        }else if (season==3){
            month1 = "7";
            month2 = "8";
            month3 = "9";
        }else if(season==4){
            month1 = "10";
            month2 = "11";
            month3 = "12";
        }
        return baseMapper.statisticsTeacher(id,month1,month2,month3);
    }
}