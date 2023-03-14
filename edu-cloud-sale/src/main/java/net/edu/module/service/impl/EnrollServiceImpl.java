package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.edu.framework.common.page.PageResult;
import net.edu.module.convert.EnrollConvert;
import net.edu.module.dao.EnrollDao;
import net.edu.module.entity.EnrollEntity;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.EnrollQuery;
import net.edu.module.service.EnrollService;
import net.edu.module.service.UserService;
import net.edu.module.vo.EnrollVO;
import net.edu.module.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weng
 * @date 2023/1/13 - 13:20
 **/
@Service
@AllArgsConstructor
public class EnrollServiceImpl extends ServiceImpl<EnrollDao, EnrollEntity> implements EnrollService {

    @Autowired
    private UserService userService;
    @Override
    public PageResult<EnrollVO> page(EnrollQuery query) {
        Page<EnrollVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<EnrollVO> list = baseMapper.selectEnrollByPage(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }


    @Override
    public void update(EnrollVO vo) {
        EnrollEntity entity = EnrollConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional
    public void abandon(Long id) {
        LambdaUpdateWrapper<EnrollEntity> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(EnrollEntity::getStatus, 2).eq(EnrollEntity::getSysId,id);
        List<Long> idList = new ArrayList<>();
        idList.add(id);
        //如果这个学员在enroll表中，直接修改状态为2，并删除系统表学员
        if(baseMapper.selectEnroll(id) != null){
            this.update(lambdaUpdateWrapper);
        }
        //如果不在enroll表中，需要在enroll中新增一条记录
        else {
            UserEntity userEntity = userService.getById(id);
            EnrollEntity enrollEntity = new EnrollEntity();
            enrollEntity.setSysId(userEntity.getId());
            enrollEntity.setRealName(userEntity.getRealName());
            enrollEntity.setPhone(userEntity.getMobile());
            enrollEntity.setArea(userEntity.getArea());
            enrollEntity.setGrade(userEntity.getGrade());
            enrollEntity.setAge(userEntity.getAge());
            enrollEntity.setAddress(userEntity.getAddress());
            enrollEntity.setStatus(2);
            baseMapper.insert(enrollEntity);
        }
        userService.delete(idList);//从系统表删除该学员
    }


}