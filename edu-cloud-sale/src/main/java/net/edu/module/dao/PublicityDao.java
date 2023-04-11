package net.edu.module.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.ReferenceEntity;
import net.edu.module.query.PublicityQuery;
import net.edu.module.vo.ReferenceVO;
import net.edu.module.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PublicityDao extends BaseDao<ReferenceEntity> {

    IPage<ReferenceVO> page(Page<ReferenceVO> page, PublicityQuery query);

    IPage<UserVO> detail(Page<UserVO> page, PublicityQuery query);
}
