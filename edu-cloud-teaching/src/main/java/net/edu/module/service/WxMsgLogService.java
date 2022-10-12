package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.vo.WxMsgLogVO;
import net.edu.module.query.WxMsgLogQuery;
import net.edu.module.entity.WxMsgLogEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消息推送
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-10-11
 */
@Service
public interface WxMsgLogService extends BaseService<WxMsgLogEntity> {

    PageResult<WxMsgLogEntity> page(WxMsgLogQuery query);

    WxMsgLogEntity selectById(Long id);

    int delete(List<Long> idList);
}