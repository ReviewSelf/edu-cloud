package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.constant.Constant;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.UserConvert;
import net.edu.module.dao.UserDao;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.RoleUserQuery;
import net.edu.module.query.UserQuery;
import net.edu.module.service.UserRoleService;
import net.edu.module.service.StudentService;
import net.edu.module.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StudentServiceImpl extends BaseServiceImpl<UserDao, UserEntity> implements StudentService {

    private final UserRoleService userRoleService;
    @Autowired
    private UserDao userDao;

    @Override
    public PageResult<UserVO> SelectStudentList(UserQuery query) {
        Page<UserVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<UserVO> list = userDao.SelectStudentList(page,query);
        return new PageResult<>(list.getRecords(), page.getTotal());
    }

    private Map<String, Object> getParams(UserQuery query) {
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
    public void save(UserVO vo) {
        UserEntity entity = UserConvert.INSTANCE.convert(vo);

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
        userRoleService.saveOrUpdate(entity.getId(), vo.getRoleIdList());

    }

    @Override
    public void update(UserVO vo) {
        UserEntity entity = UserConvert.INSTANCE.convert(vo);

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
        userRoleService.saveOrUpdate(entity.getId(), vo.getRoleIdList());

    }

    @Override
    public void delete(List<Long> idList) {
        // 删除用户
        removeByIds(idList);

        // 删除用户角色关系
        userRoleService.deleteByUserIdList(idList);

    }

    @Override
    public UserVO getByMobile(String mobile) {
        UserEntity user = baseMapper.getByMobile(mobile);

        return UserConvert.INSTANCE.convert(user);
    }

    @Override
    public void updatePassword(Long id, String newPassword) {
        // 修改密码
        UserEntity user = getById(id);
        user.setPassword(newPassword);

        updateById(user);
    }

    @Override
    public PageResult<UserVO> roleUserPage(RoleUserQuery query) {
        // 查询参数
        Map<String, Object> params = getParams(query);
        params.put("roleId", query.getRoleId());

        // 分页查询
        IPage<UserEntity> page = getPage(query);
        params.put(Constant.PAGE, page);

        // 数据列表
        List<UserEntity> list = baseMapper.getRoleUserList(params);

        return new PageResult<>(UserConvert.INSTANCE.convertList(list), page.getTotal());
    }

    @Override
    public void updateSubmitCorrectTimes(Long userId, Integer correct) {
        userDao.updateSubmitCorrectTimes(userId,correct);
    }

}
