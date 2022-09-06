package net.edu.module.api;



import net.edu.module.api.fallback.EduFileApiFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "edu-cloud-file", fallbackFactory = EduFileApiFallBack.class)
public interface EduFileApi {

    @PostMapping("/sample/upload")
    String upload(@RequestParam("file") MultipartFile[] files);
}
