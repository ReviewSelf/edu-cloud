package net.edu.system.dao;

import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.system.entity.SysLogLoginEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志
 *
 * @author 阿沐 babamu@126.com
 */
@Mapper
public interface SysLogLoginDao extends BaseDao<SysLogLoginEntity> {

}