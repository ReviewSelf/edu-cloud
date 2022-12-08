package net.edu.module.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.deepoove.poi.util.PoitlIOUtils;
import lombok.SneakyThrows;
import net.edu.module.vo.ArchiveAssessScoreBookWeightList;
import net.edu.module.vo.ArchivePlanItemVo;
import net.edu.module.vo.ArchiveScoreBookVO;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;


@Component
public class WordUtil {
    public static String WORD_TEMPLATE_PATH="E:\\template";


    @SneakyThrows
    public static void createTeachingCalendarWord(List<ArchivePlanItemVo> archivePlanItemVoList, HttpServletResponse response) {

        System.out.println(archivePlanItemVoList);
        for(int i=0;i<archivePlanItemVoList.size();i++){
            archivePlanItemVoList.get(i).setDate("11/10");
            archivePlanItemVoList.get(i).setWeeks(i);
            archivePlanItemVoList.get(i).setHours1(String.valueOf(archivePlanItemVoList.get(i).getDuration()/60));
        }
        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        Configure config = Configure.builder()
                .bind("archivePlanItemVoList", policy).build();
        String path= WORD_TEMPLATE_PATH+ File.separator +"TeachingTemplate.docx";
        XWPFTemplate template = XWPFTemplate.compile(path,config).render(
                new HashMap<String, Object>() {{
                    put("archivePlanItemVoList",archivePlanItemVoList);
                }}
        );

        System.out.println(path);
        response.setContentType("application/octet-stream");
        String name = URLEncoder.encode("教学日历.docx", "UTF-8");
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

    @SneakyThrows
    public static void createScoreBookWord(List<ArchiveAssessScoreBookWeightList> archiveAssessScoreBookWeightLists, ArchiveScoreBookVO archiveScoreBookVO, HttpServletResponse response) {

        System.out.println(archiveScoreBookVO.getClassSchedule());
        JSONArray classSchedule=JSONUtil.parseArray(archiveScoreBookVO.getClassSchedule());
        System.out.println(classSchedule);
        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        Configure config = Configure.builder()
                .bind("classSchedule", policy).bind("archiveAssessScoreBookWeightLists", policy).build();
        String path= WORD_TEMPLATE_PATH+ File.separator +"scoreBookTemplate.docx";
        XWPFTemplate template = XWPFTemplate.compile(path,config).render(
                new HashMap<String, Object>() {{
                    put("className", archiveScoreBookVO.getClassName());
                    put("courseName","课程设计");
                    put("teacherName",archiveScoreBookVO.getTeacherName());
                    put("majorName",archiveScoreBookVO.getMajorName());
                    put("classSchedule",classSchedule);
                    put("weight1",archiveAssessScoreBookWeightLists.get(0).getWeight());
                    put("weight2",archiveAssessScoreBookWeightLists.get(1).getWeight());
                    put("teachingNotes",archiveScoreBookVO.getTeachingNotes());
                    put("answerNotes",archiveScoreBookVO.getAnswerNotes());
                }}
        );

        System.out.println(path);
        response.setContentType("application/octet-stream");
        String name = URLEncoder.encode("记分册.docx", "UTF-8");
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
