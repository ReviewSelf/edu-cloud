package net.edu.framework.common.utils;

import cn.hutool.core.codec.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

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


    /**
     * MD5加密
     * @param data
     * @return
     */
    public static String MD5(String data)  {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }

            return sb.toString().toUpperCase();
        } catch (Exception exception) {
        }
        return null;

    }
}
