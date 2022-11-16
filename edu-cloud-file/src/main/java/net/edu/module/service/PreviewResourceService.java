package net.edu.module.service;

import lombok.SneakyThrows;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.utils.EncryptUtils;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.common.utils.ResponseHeadUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class PreviewResourceService {
    final RedisUtils redisUtils;

    public PreviewResourceService(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }


    public String getFileContent(String path) {
        return null;
    }

    @SneakyThrows
    public String getPDF1(String path) {
        String str = null;
        str = (String) redisUtils.get(path, RedisUtils.HOUR_ONE_EXPIRE);
        if (str == null) {
            str = EncryptUtils.getFileBase64(path);
            redisUtils.set(path, str, RedisUtils.HOUR_ONE_EXPIRE);
        }
        return str;
    }

    @SneakyThrows
    public void getPDF2(String path, HttpServletResponse response) {
        String bufferString = null;
        File file = new File(path);
        if (!file.exists()) {
            throw new ServerException("文件不存在");
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStream fis = new BufferedInputStream(fileInputStream);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        bufferString = new String(buffer);
        ResponseHeadUtils.responsePDFHead(response, new File(path).getName());
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(buffer);
        outputStream.flush();
//        String bufferString=null;
//        bufferString = (String) redisUtils.get(RedisKeys.getPdf(path), RedisUtils.HOUR_ONE_EXPIRE);
//        if (bufferString == null) {
//            File file = new File(path);
//            if (!file.exists()) {
//                throw new ServerException("文件不存在");
//            }
//            FileInputStream fileInputStream = new FileInputStream(file);
//            InputStream fis = new BufferedInputStream(fileInputStream);
//            byte[] buffer = new byte[fis.available()];
//            fis.read(buffer);
//            fis.close();
//            bufferString=new String(buffer);
//            redisUtils.set(RedisKeys.getPdf(path),bufferString, RedisUtils.HOUR_ONE_EXPIRE);
//        }
//        ResponseUtils.responsePDFHead(response, new File(path).getName());
//        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
//        outputStream.write(bufferString.getBytes());
//        outputStream.flush();
    }
}
