package net.edu.module.controller;

import lombok.extern.slf4j.Slf4j;
import net.edu.framework.common.utils.QrCodeUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author weng
 * @date 2023/4/7 - 11:36
 **/
@RestController
@RequestMapping("/qr-code")
@Slf4j
public class QrCodeController {

    @Value("${wechat.classUrl}")
    private String url;

    /**
     * 方法二：响应文件流
     *
     * @param response response原生响应文件流
     */
    @GetMapping("getPromotionQR")
    public void getPromotionQR(HttpServletResponse response, @RequestParam("id")Long id) throws IOException {
        byte[] qrCode = null;
        //拼接url，传参
        String newUrl = url+"?id=" + id;
        try {
            qrCode = QrCodeUtil.generateQrCodeByte(newUrl, 350, 350);
        } catch (Exception e) {
            log.info("Exception:{}", e.getMessage());
        }
        // Header设置文件类型（ContentType不设置也没事）
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.getOutputStream().write(Objects.requireNonNull(qrCode));
    }
}