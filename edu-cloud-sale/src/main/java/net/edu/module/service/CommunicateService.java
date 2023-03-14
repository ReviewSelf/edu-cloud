package net.edu.module.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.edu.framework.common.page.PageResult;
import net.edu.module.entity.CommunicateEntity;
import net.edu.module.query.CommunicateQuery;
import net.edu.module.vo.CommunicateVO;
import org.springframework.stereotype.Service;

/**
 * @author weng
 * @date 2023/2/5 - 10:41
 **/
@Service
public interface CommunicateService extends IService<CommunicateEntity> {

    PageResult<CommunicateVO> page(CommunicateQuery query);

    CommunicateVO getCommunicateInfo(Long id);
}