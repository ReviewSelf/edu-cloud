package net.edu.module.service.impl;

import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.ArchiveExamAttendLogDao;
import net.edu.module.dao.ArchiveExamDao;
import net.edu.module.entity.ArchiveExamAttendLogEntity;
import net.edu.module.service.ArchiveExamAttendLogService;
import net.edu.module.utils.ExamExcelUtil;
import net.edu.module.vo.ArchiveExamAttendLogVO;
import net.edu.module.vo.ArchiveExamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weng
 * @date 2022/11/10 - 16:25
 **/
@Service
@AllArgsConstructor
public class ArchiveExamAttendLogServiceImpl extends BaseServiceImpl<ArchiveExamAttendLogDao, ArchiveExamAttendLogEntity>  implements ArchiveExamAttendLogService {
    @Autowired
    private ArchiveExamAttendLogDao archiveExamAttendLogDao;

    @Autowired
    private ArchiveExamDao archiveExamDao;

    @Override
    public Integer insertExamAttendLog() {
        return archiveExamAttendLogDao.insertExamAttendLog();
    }

    @Override
    public List<ArchiveExamAttendLogVO> selectExamAttendLogByExamId(Long examId) {
        return archiveExamAttendLogDao.selectExamAttendLogByExamId(examId);
    }

    @Override
    public void exportExam(String[] examId , String[]  classId  ,  HttpServletResponse response) throws IOException {
        List<ArchiveExamAttendLogVO> data = new ArrayList<>();
        int k = 0;//保存学生个数
        for (String s : classId) {
            //获取每个班的学生的学号和姓名
            List<ArchiveExamAttendLogVO> stuId = archiveExamAttendLogDao.selectUserIdList(Long.valueOf(s));
            data.addAll(stuId);
            k += stuId.size();
        }
        List<List<ArchiveExamAttendLogVO>> ans = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            List<ArchiveExamAttendLogVO> a = new ArrayList<>();
            a.add(data.get(i));
            ans.add(a);
        }
        List<List<String>> res = new ArrayList<>();
        for (List<ArchiveExamAttendLogVO> an : ans) {
            for (int j = 0; j < ans.get(0).size(); j++) {
                List<String> x = new ArrayList<>();
                x.add(an.get(j).getUserNumber().toString());
                x.add(an.get(j).getUserName());
                res.add(x);
            }
        }
        for (int i = 0; i < ans.size(); i++) {
            for (int j = 0; j < examId.length; j++) {
                res.get(i).add("0");
            }
        }
        for (int q = 0; q < examId.length; q++) {
            //查询学生考试情况数据
            List<ArchiveExamAttendLogVO> vos = archiveExamAttendLogDao.selectExamAttendLogByExamId(Long.valueOf(examId[q]));
            System.out.println(vos);
            for (ArchiveExamAttendLogVO vo : vos) {
                for (List<String> re : res) {
                    if (re.get(0).equals(vo.getUserNumber().toString())) {
                        re.set(q + 2, vo.getScore().toString());
                        break;
                    }
                }
            }
        }
        List<String> header = new ArrayList<>();
        for (String s : examId) {
            ArchiveExamVO archiveExamVO = archiveExamDao.selectExamById(Long.valueOf(s));
            String name = archiveExamVO.getName();
            header.add(name);
        }
        String bigTitle = "考试成绩";
        ExamExcelUtil.examExportExcel(header,res,bigTitle,response);
    }
}