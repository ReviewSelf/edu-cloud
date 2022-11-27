package net.edu.module.utils;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.deepoove.poi.util.PoitlIOUtils;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;


@Component
public class WordUtil {
    public static String WORD_TEMPLATE_PATH="E:\\template";


    @SneakyThrows
    public static void createTeachingCalendarWord(HttpServletResponse response) {

        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        Configure config = Configure.builder()
                .bind("epaAreaEntities", policy).bind("focusOrderEntities", policy).build();
        String path= WORD_TEMPLATE_PATH+ File.separator +"TeachingTemplate.docx";
        XWPFTemplate template = XWPFTemplate.compile(path,config).render(
                new HashMap<String, Object>() {{
//                    put("title", "宁波生态环境简报");
//                    put("year",statisticsEntities.getReportYear());
//                    put("stage",statisticsEntities.getReportPeriod());
//                    put("stageSum",statisticsEntities.getReportPeriod());
//                    put("date",new SimpleDateFormat("yyyy年M月d日").format(date));
//                    put("date_interval",Integer.parseInt(String.format("%tY",date1))+"."+Integer.parseInt(String.format("%tm",date1))+"."+Integer.parseInt(String.format("%td",date1))+"-"+Integer.parseInt(String.format("%tm",date2))+"."+Integer.parseInt(String.format("%td",date2)));
//                    put("summary",statisticsEntities.getSummary());
//                    put("epaAreaEntities",epaAreaEntities);
//                    put("focusOrderEntities",focusOrderEntities);
                }}
        );

        System.out.println(path);
        response.setContentType("application/octet-stream");
        String name = URLEncoder.encode("宁波市生态环境信访周报.docx", "UTF-8");
        response.setHeader("access-control-expose-headers", "content-disposition");
        response.setHeader("content-disposition", "attachment;filename=" + name + ".docx");
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
        template.close();
        bos.close();
        out.close();
    }
}
