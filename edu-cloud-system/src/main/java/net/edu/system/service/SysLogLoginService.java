package net.edu.system.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.system.entity.SysLogLoginEntity;
import net.edu.system.query.SysLogLoginQuery;
import net.edu.system.vo.SysLogLoginVO;

/**
 * 登录日志
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysLogLoginService extends BaseService<SysLogLoginEntity> {

    PageResult<SysLogLoginVO> page(SysLogLoginQuery query);

    /**
     * 保存登录日志
     *
     * @param username  用户名
     * @param status    登录状态
     * @param operation 操作信息
     */
    void save(String username, Integer status, Integer operation);
}