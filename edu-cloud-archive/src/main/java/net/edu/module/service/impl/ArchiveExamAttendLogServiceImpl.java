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
    public void exportExam(List<Long> examId, HttpServletResponse response) throws IOException {
        List<List<ArchiveExamAttendLogVO>> data = new ArrayList<>();
        List<String> header = new ArrayList<>();
        for (Long aLong : examId) {
            //查询学生考试情况数据
            List<ArchiveExamAttendLogVO> k = archiveExamAttendLogDao.selectExamAttendLogByExamId(aLong);
            if (k != null) {
                data.add(k);
            }
        }

        for (int j = 0;j<examId.size();j++){
            ArchiveExamVO archiveExamVO = archiveExamDao.selectExamById(examId.get(j));
            String name = archiveExamVO.getName();
            header.add(name);
        }
        String bigTitle = "考试成绩";
        ExamExcelUtil.examExportExcel(header,data,bigTitle,response);
    }
}