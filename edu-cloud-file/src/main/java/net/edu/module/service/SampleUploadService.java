package net.edu.module.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/7 12:49
 * @Version: 1.0
 * @Description:
 */
@Service
public class SampleUploadService {

    String pathPrefix="E:\\sample";

    @SneakyThrows
    public  String upload(MultipartFile file, String fileName){
        String path=pathPrefix+File.separator+fileName;
        File newFile=new File(path);
        // 没有目录，则自动创建目录
        File parent = newFile.getParentFile();
        if (parent != null && !parent.mkdirs() && !parent.isDirectory()) {
            throw new IOException("目录 '" + parent + "' 创建失败");
        }
        file.transferTo(newFile);
        return path;
    }
}
