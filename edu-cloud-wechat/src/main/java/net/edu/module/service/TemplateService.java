package net.edu.module.service;

import net.edu.module.vo.ClassOpenVO;
import org.springframework.stereotype.Service;

/**
 * @author weng
 * @date 2022/9/26 - 15:35
 **/
@Service
public interface TemplateService {

    int insertMsgLogClassOpenTemplate(ClassOpenVO classOpenVO);

    void sentTemplate();
}
