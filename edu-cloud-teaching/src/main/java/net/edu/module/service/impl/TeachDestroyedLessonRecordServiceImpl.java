package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.Result;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.api.EduSaleApi;
import net.edu.module.convert.TeachDestroyedLessonRecordConvert;
import net.edu.module.dao.TeachDestroyedLessonRecordDao;
import net.edu.module.dao.UserDao;
import net.edu.module.entity.TeachDestroyedLessonRecordEntity;
import net.edu.module.query.TeachDestroyedLessonRecordQuery;
import net.edu.module.service.TeachClassHoursFlowRecordService;
import net.edu.module.service.TeachDestroyedLessonRecordService;
import net.edu.module.vo.TeachClassHoursFlowRecordVO;
import net.edu.module.vo.TeachClassHoursVO;
import net.edu.module.vo.TeachDestroyedLessonRecordListVO;
import net.edu.module.vo.TeachDestroyedLessonRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 消课管理
 *
 * @author sqw 
 * @since 1.0.0 2023-03-04
 */
@Service
@AllArgsConstructor
public class TeachDestroyedLessonRecordServiceImpl extends BaseServiceImpl<TeachDestroyedLessonRecordDao, TeachDestroyedLessonRecordEntity> implements TeachDestroyedLessonRecordService {

    private final EduSaleApi eduSaleApi;

    private final UserDao userDao;

    private final TeachDestroyedLessonRecordDao teachDestroyedLessonRecordDao;
    private final TeachClassHoursFlowRecordService teachClassHoursFlowRecordService;

    @Override
    public PageResult<TeachDestroyedLessonRecordVO> page(TeachDestroyedLessonRecordQuery query) {
        IPage<TeachDestroyedLessonRecordVO> page = baseMapper.selectDestroyedLessonRecordPage(getPage(query), query);

        return new PageResult<>(page.getRecords(), page.getTotal());
    }

    @Override
    public void save(TeachDestroyedLessonRecordVO vo) {
        TeachDestroyedLessonRecordEntity entity = TeachDestroyedLessonRecordConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }
    @Override
    public void addRecord(List<TeachDestroyedLessonRecordVO> list) {
        System.out.println("asd");
        for(TeachDestroyedLessonRecordVO item : list){
            TeachDestroyedLessonRecordEntity entity = TeachDestroyedLessonRecordConvert.INSTANCE.convert(item);
            baseMapper.insert(entity);
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TeachDestroyedLessonRecordVO vo) {
        vo.setStatus(1);
        //更新消课记录表
        TeachDestroyedLessonRecordEntity entity = TeachDestroyedLessonRecordConvert.INSTANCE.convert(vo);
        updateById(entity);
        //计算扣费情况
        Result<TeachClassHoursVO> res = eduSaleApi.getStudentPay(vo.getStuId()); //获取用户余额
        TeachClassHoursVO vo1 = res.getData();
        TeachClassHoursVO vo3 = new TeachClassHoursVO();
        BeanUtils.copyProperties(vo1,vo3);
        if(vo1 == null){
            throw new ServerException("该用户没有课时信息！");
        }
        if(vo.getType() == 0){ //常规课
                if(vo1.getNormal().compareTo(vo.getActualDeduction()) == -1){ //常规购买课时不够
                    if(vo1.getNormalPresent().compareTo(vo.getActualDeduction().subtract(vo1.getNormal())) == -1){
                        //如果剩余赠送常规课时加上剩余购买常规课时还不够
                        throw new ServerException("学员余额不足！");
                    }else{
                        //赠送课时扣除购买课时不够的部分，然后扣除购买的常规课时
                        vo1.setNormalPresent(vo1.getNormalPresent().subtract(vo.getActualDeduction().subtract(vo1.getNormal())));
                        vo1.setNormal(vo1.getNormal().subtract(vo1.getNormal()));
                    }
                }else{
                    vo1.setNormal(vo1.getNormal().subtract(vo.getActualDeduction())); //如果够直接减去实际课时
                }
        }else if(vo.getType() == 1){ //集训课
                if(vo1.getTraining().compareTo(vo.getActualDeduction()) == -1){ //集训购买课时不够
                    if(vo1.getTrainingPresent().compareTo(vo.getActualDeduction().subtract(vo1.getTraining())) == -1){
                        //如果剩余赠送集训课时加上剩余购买集训课时还不够,开始算上常规课程
                        if(vo1.getNormal().compareTo(vo.getActualDeduction().subtract(vo1.getTraining().add(vo1.getTrainingPresent()))) == -1){
                            if(vo1.getNormalPresent().compareTo(vo.getActualDeduction().subtract(vo1.getNormal().add(vo1.getTraining().add(vo1.getTrainingPresent())))) == -1){
                                throw new ServerException("学员余额不足！");
                            }else{
                                vo1.setNormalPresent(vo1.getNormalPresent().subtract(vo.getActualDeduction().subtract(vo1.getNormal().add(vo1.getTraining().add(vo1.getTrainingPresent())))));
                                vo1.setNormal(vo1.getNormal().subtract(vo1.getNormal()));
                            }
                        }else{
                            vo1.setNormal(vo1.getNormal().subtract(vo.getActualDeduction().subtract(vo1.getTraining().add(vo1.getTrainingPresent()))));
                        }
                        vo1.setTraining(vo1.getTraining().subtract(vo1.getTraining()));
                        vo1.setTrainingPresent(vo1.getTrainingPresent().subtract(vo1.getTrainingPresent()));
                    }else{
                        //赠送课时扣除购买课时不够的部分，然后扣除购买的集训课时
                        vo1.setTrainingPresent(vo1.getTrainingPresent().subtract(vo.getActualDeduction().subtract(vo1.getTraining())));
                        vo1.setTraining(vo1.getTraining().subtract(vo1.getTraining()));
                    }
                }else{
                    vo1.setTraining(vo1.getTraining().subtract(vo.getActualDeduction()));//直接减去实际课时
                }
        }
        //扣费
        userDao.updateUserClassHours(vo1, SecurityUser.getUserId());
        //记录扣费流水
        System.out.println(vo1);
        System.out.println(res.getData());
        TeachClassHoursFlowRecordVO vo2 = new TeachClassHoursFlowRecordVO();
        vo2.setUserId(vo.getStuId());
        vo2.setLessonId(vo.getLessonId());
        vo2.setDirection(vo.getDirection());
        vo2.setType(2);
        vo2.setNormal(vo3.getNormal().subtract(vo1.getNormal()));
        vo2.setNormalPresent(vo3.getNormalPresent().subtract(vo1.getNormalPresent()));
        vo2.setTraining(vo3.getTraining().subtract(vo1.getTraining()));
        vo2.setTrainingPresent(vo3.getTrainingPresent().subtract(vo1.getTrainingPresent()));
        teachClassHoursFlowRecordService.save(vo2);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public void saveList(TeachDestroyedLessonRecordListVO vo) {
        if(vo.getStuIdList()!=null && !vo.getStuIdList().isEmpty()){
            for(int i = 0;i<vo.getStuIdList().size();i++){
                vo.getVo().setStuId(vo.getStuIdList().get(i));
                save(vo.getVo());
            }
        }
    }

}