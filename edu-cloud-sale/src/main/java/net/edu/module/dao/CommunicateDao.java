package net.edu.module.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.CommunicateEntity;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.CommunicateQuery;
import net.edu.module.query.UserQuery;
import net.edu.module.vo.CommunicateVO;
import net.edu.module.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author weng
 * @date 2023/1/13 - 13:21
 **/
@Mapper
public interface CommunicateDao extends BaseDao<CommunicateEntity> {

    IPage<CommunicateVO> page(Page<CommunicateVO> page, CommunicateQuery query);

}