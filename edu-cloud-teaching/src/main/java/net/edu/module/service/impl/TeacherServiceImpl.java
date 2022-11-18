package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.constant.Constant;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.TeacherConvert;
import net.edu.module.dao.UserDao;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.TeacherQuery;
import net.edu.module.service.RoleService;
import net.edu.module.service.TeacherService;
import net.edu.module.vo.AllTeacherVO;
import net.edu.module.vo.TeacherVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class TeacherServiceImpl extends BaseServiceImpl<UserDao, UserEntity> implements TeacherService {

    private final RoleService roleService;



    @Override
    public PageResult<TeacherVO> TeacherPage(TeacherQuery query) {
        Page<TeacherVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<TeacherVO> list = baseMapper.selectTeacherList(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }

    private Map<String, Object> getParams(TeacherQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", query.getUsername());
        params.put("mobile", query.getMobile());
        params.put("gender", query.getGender());
        params.put("roleId", query.getRoleId());

        // 数据权限
        params.put(Constant.DATA_SCOPE, getDataScope("t1", null));

        return params;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(TeacherVO vo) {
        UserEntity entity = TeacherConvert.INSTANCE.convert(vo);

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
    public void update(TeacherVO vo) {
        UserEntity entity = TeacherConvert.INSTANCE.convert(vo);

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

    @Override
    public void delete(List<Long> idList) {
        // 删除用户
        removeByIds(idList);

        // 删除用户角色关系
        roleService.deleteByUserIdList(idList);

    }

    @Override
    public void  resetPassword(String id,String password){
        UserEntity user=getById(id);
        user.setPassword(password);
        updateById(user);
    }

    @Override
    public List<AllTeacherVO> GetTeacher(){
        List<AllTeacherVO> allTeacherVo=baseMapper.getTeacher();
        return allTeacherVo;
    }

}
