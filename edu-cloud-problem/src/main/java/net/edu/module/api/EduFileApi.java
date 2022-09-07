package net.edu.module.api;



import net.edu.module.api.fallback.EduFileApiFallBack;
import net.edu.module.vo.FileUploadVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(value = "edu-cloud-file", fallbackFactory = EduFileApiFallBack.class)
public interface EduFileApi {

    @PostMapping(value = "/sample/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    FileUploadVO upload(@RequestPart("file") MultipartFile file, @RequestParam("problemId")String problemId);



}
