package net.edu.module.api;

import net.edu.framework.common.utils.Result;
import net.edu.module.fallback.EduArchiveApiFallBack;
import net.edu.module.vo.ClassHoursFlowRecordVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "edu-cloud-sale", fallbackFactory = EduArchiveApiFallBack.class)
public interface EduSaleApi {
    /****************************teach调用******************************************/

}

