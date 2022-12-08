package net.edu.framework.common.utils;

import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @Author: 马佳浩
 * @Date: 2022/9/7 14:18
 * @Version: 1.0
 * @Description:
 */
public class ResponseHeadUtils {
    @SneakyThrows
    public static void responseFileHead(HttpServletResponse response, String name) {
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setCharacterEncoding("utf-8");
    }

    @SneakyThrows
    public static void responseTXTHead(HttpServletResponse response, String name) {
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        response.setContentType("text/plain; charset=utf-8");
        response.setCharacterEncoding("utf-8");
    }

    @SneakyThrows
    public static void responsePDFHead(HttpServletResponse response, String name) {
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        response.setContentType("application/pdf; charset=utf-8");
        response.setCharacterEncoding("utf-8");
    }

    @SneakyThrows
    public static void responseEXCELHead(HttpServletResponse response, String name) {
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        response.setContentType("application/vnd.ms-excel; charset=utf-8");
        response.setCharacterEncoding("utf-8");
    }

}
