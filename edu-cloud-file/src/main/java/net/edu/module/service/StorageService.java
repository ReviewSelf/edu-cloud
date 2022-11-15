package net.edu.module.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileNameUtil;
import lombok.SneakyThrows;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.utils.ResponseHeadUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 *
 */
public abstract class StorageService {

    /**
     * 根据文件名，生成带时间戳的新文件名
     *
     * @param fileName 文件名
     * @return 返回带时间戳的文件名
     */
    public String getNewFileName(String fileName) {
        // 主文件名，不包含扩展名
        String prefix = FileNameUtil.getPrefix(fileName);
        // 文件扩展名
        String suffix = FileNameUtil.getSuffix(fileName);
        // 把当天HH:mm:ss，转换成秒
        long time = DateUtil.timeToSecond(DateUtil.formatTime(new Date()));
        // 新文件名
        return prefix + "_" + time + "." + suffix;
    }

    /**
     * 生成路径，不包含文件名
     *
     * @return 返回生成的路径
     */
    public String getPath() {
        // 文件路径
        String path = DateUtil.format(new Date(), "yyyyMMdd");

//        // 如果有前缀，则也带上
//        if (StringUtils.hasText(properties.getConfig().getPrefix())) {
//            path = properties.getConfig().getPrefix() + "/" + path;
//        }

        return path;
    }

    /**
     * 根据文件名，生成路径
     *
     * @param fileName 文件名
     * @return 生成文件路径
     */
    public String getPath(String fileName) {
        return getPath() + "/" + getNewFileName(fileName);
    }

    /**
     * 文件上传
     *
     * @param data 文件字节数组
     * @param path 文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 内部文件上传
     *
     * @param file 文件流
     * @param fileName 文件名称
     * @return 返回本地url
     */
    public abstract String upload2(MultipartFile file, String fileName);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param path        文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(InputStream inputStream, String path);


    @SneakyThrows
    public void previewUrl(String path, HttpServletResponse response) {
        File file = new File(path);
        if (!file.exists()) {
            throw new ServerException("文件不存在");
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStream fis = new BufferedInputStream(fileInputStream);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        ResponseHeadUtils.responsePDFHead(response, file.getName());
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(buffer);
        outputStream.flush();
    }
}
