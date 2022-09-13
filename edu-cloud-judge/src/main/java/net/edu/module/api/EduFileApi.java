package net.edu.module.api;




import io.swagger.v3.oas.annotations.Operation;
import net.edu.framework.common.utils.Result;
import net.edu.module.api.fallback.EduFileApiFallBack;

import net.edu.module.vo.CodeSampleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "edu-cloud-file", fallbackFactory = EduFileApiFallBack.class)
public interface EduFileApi {


    @GetMapping("/sample/fileBase64")
    @Operation(summary = "测试样例转Base64")
    String getFileContent(@RequestParam("path") String path);


}
