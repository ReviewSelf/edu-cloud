package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.edu.framework.common.constant.Constant;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.AffairTeacherConvert;
import net.edu.module.convert.TeacherConvert;
import net.edu.module.dao.UserDao;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.AffairTeacherQuery;
import net.edu.module.query.TeacherQuery;
import net.edu.module.service.RoleService;
import net.edu.module.service.affairTeacherService;
import net.edu.module.vo.TeacherVO;
import net.edu.module.vo.affairTeacherVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @author: sl
 * @date: 2022年10月10日 16:51
 */
@Service
@AllArgsConstructor
public class affairTeacherServiceImpl extends BaseServiceImpl<UserDao, UserEntity> implements affairTeacherService {


    private final RoleService roleService;
    @Override
    public PageResult<affairTeacherVO> affairTeacherPage(AffairTeacherQuery query){

        // 查询参数
        Map<String, Object> params = getParams(query);

        // 分页查询
        IPage<UserEntity> page = getPage(query);
        params.put(Constant.PAGE, page);

        // 数据列表
        List<affairTeacherVO> list = baseMapper.getAffairTeacherList(params);

        return new PageResult<>(list, page.getTotal());
    }

    private Map<String, Object> getParams(AffairTeacherQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", query.getUsername());
        params.put("mobile", query.getMobile());
        params.put("gender", query.getGender());

        // 数据权限
        params.put(Constant.DATA_SCOPE, getDataScope("t1", null));

        return params;
    }

    @Override
    public void  resetPassword(String id,String password){
        UserEntity user=getById(id);
        user.setPassword(password);
        updateById(user);
    }

    @Override
    public void delete(List<Long> idList) {
        // 删除用户
        removeByIds(idList);

        // 删除用户角色关系
        roleService.deleteByUserIdList(idList);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(affairTeacherVO vo) {
        UserEntity entity = AffairTeacherConvert.INSTANCE.convert(vo);

        // 判断用户名是否存在
        UserEntity user = baseMapper.getByUsername(entity.getUsername());
        if (user != null) {
            throw new ServerException("用户名已经存在");
        }

        // 判断手机号是否存在
        user = baseMapper.getByMobile(entity.getMobile());
        if (user != null) {
            throw new ServerException("手机号已经存在");
        }

        // 保存用户
        baseMapper.insert(entity);

        // 保存用户角色关系
        roleService.saveOrUpdate(entity.getId(), vo.getRoleIdList());

    }

    @Override
    public void update(affairTeacherVO vo) {
        UserEntity entity = AffairTeacherConvert.INSTANCE.convert(vo);

        // 判断用户名是否存在
        UserEntity user = baseMapper.getByUsername(entity.getUsername());
        if (user != null && !user.getId().equals(entity.getId())) {
            throw new ServerException("用户名已经存在");
        }

        // 判断手机号是否存在
        user = baseMapper.getByMobile(entity.getMobile());
        if (user != null && !user.getId().equals(entity.getId())) {
            throw new ServerException("手机号已经存在");
        }

        // 更新用户
        updateById(entity);

        // 更新用户角色关系
        roleService.saveOrUpdate(entity.getId(), vo.getRoleIdList());

    }
}
