package net.edu.module.utils;

import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/7 14:18
 * @Version: 1.0
 * @Description:
 */
public class ResponseUtils {
    @SneakyThrows
    public static void responseFileHead(HttpServletResponse response, String name) {
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setCharacterEncoding("utf-8");
    }
}
