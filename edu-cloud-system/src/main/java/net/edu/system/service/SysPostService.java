package net.edu.system.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.system.entity.SysPostEntity;
import net.edu.system.query.SysPostQuery;
import net.edu.system.vo.SysPostVO;

import java.util.List;

/**
 * 岗位管理
 *
 * @author 阿沐 babamu@126.com
 */
public interface SysPostService extends BaseService<SysPostEntity> {

    PageResult<SysPostVO> page(SysPostQuery query);

    List<SysPostVO> getList();

    void save(SysPostVO vo);

    void update(SysPostVO vo);

    void delete(List<Long> idList);
}