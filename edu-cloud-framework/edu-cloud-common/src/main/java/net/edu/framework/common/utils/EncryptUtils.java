package net.edu.framework.common.utils;

import cn.hutool.core.codec.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
public class EncryptUtils {


    /**
     * Base64加密
     * @param toEncodeContent
     * @return
     */
    public static String encryptByBase64(String toEncodeContent) {
        if(toEncodeContent==null||"".equals(toEncodeContent)){
            return null;
        }
        try {
            byte[] textByte = toEncodeContent.getBytes("UTF-8");
            return Base64.encode(textByte);
        } catch (Exception e) {
            e.printStackTrace();
            return "加码错误";
        }
    }

    /**
     * 文件转base64
     * @param filePath
     * @return
     */
    public static String getFileBase64(String filePath) {
        InputStream in = null;
        byte[] data = null;
        // 读取文件字节数组
        try {
            in = new FileInputStream(filePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 返回 Base64 编码过的字节数组字符串
        return Base64.encode(data);
    }
}
