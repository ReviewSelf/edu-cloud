package net.edu.module.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.WxMsgLogEntity;
import net.edu.module.query.WxMsgLogQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 消息推送
*
* @author 阿沐 babamu@126.com
* @since 1.0.0 2022-10-11
*/
@Mapper
public interface WxMsgLogDao extends BaseDao<WxMsgLogEntity> {

    IPage<WxMsgLogEntity> selectWxMsgLogByPage(Page<WxMsgLogEntity> page, WxMsgLogQuery query);

    WxMsgLogEntity selectWxMsgLogById(Long id);

    int deleteWxMsgLogByIds(@Param("list") List<Long> id);
}
