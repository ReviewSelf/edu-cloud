package net.edu.module.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.edu.framework.common.page.PageResult;
import net.edu.module.entity.ReferenceEntity;
import net.edu.module.query.PublicityQuery;
import net.edu.module.vo.ReferenceVO;
import net.edu.module.vo.UserVO;
public interface PublicityService extends IService<ReferenceEntity> {
    PageResult<ReferenceVO> page(PublicityQuery query);

    PageResult<UserVO> detail(PublicityQuery query);
}
