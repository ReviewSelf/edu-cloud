package net.edu.module.api;

import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.utils.Result;
import net.edu.module.fallback.EduArchiveApiFallBack;
import net.edu.module.vo.TeachClassHoursVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "edu-cloud-sale", fallbackFactory = EduArchiveApiFallBack.class)
public interface EduSaleApi {
    /****************************teach调用******************************************/

    @GetMapping("user/pay/{id}")
    @Operation(summary = "课时信息")
    Result<TeachClassHoursVO> getStudentPay(@PathVariable("id") Long id);
}

