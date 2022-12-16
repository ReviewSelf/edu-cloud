package net.edu.module.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.Tables;
import com.deepoove.poi.data.style.BorderStyle;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.deepoove.poi.util.PoitlIOUtils;
import lombok.SneakyThrows;
import net.edu.module.vo.*;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
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
    public static void createScoreBookWord(List<ArchiveScoreInBookVO> archiveScoreInBookVOList,List<ArchiveSignVO> archiveSignVOList, List<ArchiveAssessScoreBookWeightList> archiveAssessScoreBookWeightLists, ArchiveScoreBookVO archiveScoreBookVO, HttpServletResponse response) {

        BorderStyle borderStyle = new BorderStyle();
        borderStyle.setColor("000000");
        borderStyle.setSize(5);
        borderStyle.setType(XWPFTable.XWPFBorderType.SINGLE);


        System.out.println(archiveScoreBookVO.getClassSchedule());
        JSONArray classSchedule=JSONUtil.parseArray(archiveScoreBookVO.getClassSchedule());
        System.out.println(classSchedule);
        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();

        //第一个表格，签到表
        JSONArray headList=JSONUtil.parseArray(archiveSignVOList.get(0).getRecord());
        String[] headArray=new String[headList.size()+3];
        headArray[0]="序号";
        headArray[1]="学号";
        headArray[2]="姓名";
        for(int i=0;i<headList.size();i++){
            headArray[i+3] = String.valueOf(i+1);
        }
        RowRenderData serverHeader = Rows.of(headArray).center()
                .textColor("000000").textFontSize(10).textBold().create();
        Tables.TableBuilder tableBuilder = Tables.ofA4MediumWidth().addRow(serverHeader);
        for(int i=0;i<archiveSignVOList.size();i++){
            String[] RowList=new String[headList.size()+3];
            RowList[0]=String.valueOf(i+1);
            RowList[1]=archiveSignVOList.get(i).getStuId();
            RowList[2]=archiveSignVOList.get(i).getStuName();
            JSONArray signList=JSONUtil.parseArray(archiveSignVOList.get(i).getRecord());
            for(int j=0;j<signList.size();j++){
                RowList[j+3]= (String) signList.get(j);
            }
            RowRenderData serverRow = Rows.of(RowList)
                    .center().textFontSize(11).create();
            tableBuilder.addRow(serverRow);
        }
        double[] colWidths= new double [headList.size()+3];
        colWidths[0]=1;
        colWidths[1]=3;
        colWidths[2]=2;
        for(int i=3;i<colWidths.length;i++){
            colWidths[i]=1;
        }
        System.out.println(7+colWidths.length-3);
        TableRenderData serverTable=tableBuilder.border(borderStyle).width(7+colWidths.length-3,colWidths).center().create();



        //第二个表格
        System.out.println(archiveScoreInBookVOList);
        String[] headArray2=new String[archiveScoreInBookVOList.get(0).getTestList().size()+3];
        for(int i=0;i<archiveScoreInBookVOList.get(0).getTestList().size();i++){
            headArray2[i]=archiveScoreInBookVOList.get(0).getTestList().get(i).getTestName();
        }
        headArray2[archiveScoreInBookVOList.get(0).getTestList().size()+2]="总评成绩";
        headArray2[archiveScoreInBookVOList.get(0).getTestList().size()+1]="期末成绩";
        headArray2[archiveScoreInBookVOList.get(0).getTestList().size()]="平时成绩";
        System.out.println(headArray2);
        RowRenderData serverHeader2 = Rows.of(headArray2).center()
                .textColor("000000").textFontSize(10).textBold().create();
        Tables.TableBuilder tableBuilder2 = Tables.ofA4MediumWidth().addRow(serverHeader2);
        for(int i=0;i<archiveScoreInBookVOList.size();i++){
            String[] RowList=new String[archiveScoreInBookVOList.get(0).getTestList().size()+3];
            for(int j=0;j<archiveScoreInBookVOList.get(0).getTestList().size();j++){
                RowList[j]=archiveScoreInBookVOList.get(i).getTestList().get(j).getScore();
            }
            RowList[archiveScoreInBookVOList.get(0).getTestList().size()+2]=archiveScoreInBookVOList.get(i).getTotalScore();
            RowList[archiveScoreInBookVOList.get(0).getTestList().size()+1]=archiveScoreInBookVOList.get(i).getFinalScore();
            RowList[archiveScoreInBookVOList.get(0).getTestList().size()]=archiveScoreInBookVOList.get(i).getPeaceScore();
            RowRenderData serverRow = Rows.of(RowList)
                    .center().textFontSize(11).create();
            tableBuilder2.addRow(serverRow);
        }
        double[] colWidths2= new double [archiveScoreInBookVOList.get(0).getTestList().size()+3];
        for(int i=0;i<archiveScoreInBookVOList.get(0).getTestList().size()+3;i++){
            colWidths2[i]=2;
        }
        System.out.println(archiveScoreInBookVOList.get(0).getTestList().size()+3);
        TableRenderData serverTable2=tableBuilder2.border(borderStyle).width((archiveScoreInBookVOList.get(0).getTestList().size()+3)*2,colWidths2).center().create();
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
                    put("serverTable",serverTable);
                    put("serverTable2",serverTable2);
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
