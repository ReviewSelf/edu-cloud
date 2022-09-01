package net.edu.system.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.system.entity.SysRoleEntity;
import net.edu.system.query.SysRoleQuery;
import net.edu.system.vo.SysRoleDataScopeVO;
import net.edu.system.vo.SysRoleVO;

import java.util.List;

/**
 * 角色
 * 
 * @author 阿沐 babamu@126.com
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

	PageResult<SysRoleVO> page(SysRoleQuery query);

	List<SysRoleVO> getList(SysRoleQuery query);

	void save(SysRoleVO vo);

	void update(SysRoleVO vo);

	void dataScope(SysRoleDataScopeVO vo);

	void delete(List<Long> idList);
}
