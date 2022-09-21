package net.edu.module.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.edu.framework.common.cache.RedisCache;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.utils.EncryptUtils;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.module.utils.ResponseUtils;
import net.edu.module.vo.SampleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/7 12:49
 * @Version: 1.0
 * @Description:
 */
@Service
public class SampleUploadService {

    @Value("${storage.local.samplePath}")
    String pathPrefix;

    final
    RedisUtils redisUtils;

    public SampleUploadService(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @SneakyThrows
    public String upload(MultipartFile file, String fileName) {
        String path = pathPrefix + File.separator + fileName;
        File newFile = new File(path);
        // 没有目录，则自动创建目录
        File parent = newFile.getParentFile();
        if (parent != null && !parent.mkdirs() && !parent.isDirectory()) {
            throw new IOException("目录 '" + parent + "' 创建失败");
        }
        file.transferTo(newFile);
        return path;
    }

    public List<SampleVO> uploadBatch(MultipartFile[] input, MultipartFile[] output, Long problemId) {
        List<SampleVO> sampleVOS = new ArrayList<>();
        String newFileName = problemId + File.separator + System.currentTimeMillis();
        // 文件扩展名
        for (int i = 0; i < input.length; i++) {
            String inputPath = upload(input[i], newFileName + "_" + i + ".in");
            String outPath = upload(output[i], newFileName + "_" + i + ".out");
            sampleVOS.add(new SampleVO(problemId, inputPath, outPath, input[i].getSize(), output[i].getSize()));
        }
        return sampleVOS;
    }

    @SneakyThrows
    public void download(String path, HttpServletResponse response) {
        File file = new File(path);
        if (!file.exists()) {
            throw new ServerException("文件不存在");
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStream fis = new BufferedInputStream(fileInputStream);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        ResponseUtils.responseFileHead(response, file.getName());
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(buffer);
        outputStream.flush();
    }

    public String getFileContent(String path) {
        String str=null;
        str= (String) redisUtils.get(path, RedisUtils.HOUR_ONE_EXPIRE);
        if(str==null){
            str=EncryptUtils.getFileBase64(path);
            redisUtils.set(path,str,RedisUtils.HOUR_ONE_EXPIRE);
        }
        return str;
    }


}
