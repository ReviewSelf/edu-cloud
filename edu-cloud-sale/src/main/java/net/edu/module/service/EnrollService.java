package net.edu.module.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.edu.framework.common.page.PageResult;
import net.edu.module.dao.EnrollDao;
import net.edu.module.entity.EnrollEntity;
import net.edu.module.query.EnrollQuery;
import net.edu.module.query.UserQuery;
import net.edu.module.vo.EnrollVO;
import net.edu.module.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author weng
 * @date 2023/1/13 - 13:20
 **/
public interface EnrollService extends IService<EnrollEntity> {

    PageResult<EnrollVO> page(EnrollQuery query);



    void update(EnrollVO vo);


    void abandon(Long id);

    void studentFromExcel(MultipartFile file);
}