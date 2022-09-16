package net.edu.module.service;

import lombok.SneakyThrows;
import net.edu.framework.common.exception.ServerException;
import net.edu.module.properties.LocalStorageProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 *
 */
public class LocalStorageService extends StorageService {

   LocalStorageProperties properties=new LocalStorageProperties();

    @Value("${storage.local.path}")
    private String localPath="E://upload";

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    @SneakyThrows
    public String upload2(MultipartFile file, String fileName) {
        String path=localPath+File.separator+fileName;
        File newFile=new File(path);
        // 没有目录，则自动创建目录
        File parent = newFile.getParentFile();
        if (parent != null && !parent.mkdirs() && !parent.isDirectory()) {
            throw new ServerException("目录 '" + parent + "' 创建失败");
        }
            file.transferTo(newFile);

        return path;
    }


    @Override
    public String upload(InputStream inputStream, String path) {

        try {
            File file = new File(properties.getPath() + File.separator + path);

            // 没有目录，则自动创建目录
            File parent = file.getParentFile();
            if (parent != null && !parent.mkdirs() && !parent.isDirectory()) {
                throw new IOException("目录 '" + parent + "' 创建失败");
            }

            FileCopyUtils.copy(inputStream, Files.newOutputStream(file.toPath()));
        } catch (Exception e) {
            throw new ServerException("上传文件失败：", e);
        }

        return properties.getDomain() + "/" + properties.getUrl() + "/" + path;
    }
}
