package net.edu.module.service.impl;

import lombok.AllArgsConstructor;
import net.edu.framework.common.excel.ExcelSyncDataListener;
import net.edu.framework.common.utils.ExcelUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.ArchiveWeightAssessTestDao;
import net.edu.module.dao.ArchiveWeightTargetAssessDao;
import net.edu.module.entity.ArchiveTestScoreEntity;
import net.edu.module.entity.ArchiveWeightAssessTestEntity;
import net.edu.module.vo.ArchiveTestScoreVO;
import net.edu.module.dao.ArchiveTestScoreDao;
import net.edu.module.service.ArchiveTestScoreService;
import net.edu.module.vo.ArchiveWeightAssessTestVO;
import net.edu.module.vo.ArchiveWeightTargetAssessVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
@Service
@AllArgsConstructor
public class ArchiveTestScoreServiceImpl extends BaseServiceImpl<ArchiveTestScoreDao, ArchiveTestScoreEntity> implements ArchiveTestScoreService {

    @Autowired
    private ArchiveTestScoreDao archiveTestScoreDao;
    @Autowired
    private ArchiveWeightAssessTestDao archiveWeightAssessTestDao;
    @Autowired
    private ArchiveWeightTargetAssessDao archiveWeightTargetAssessDao;


    @Override
    public List<ArchiveTestScoreVO> selectTestScoreByCourseId(Long courseId) {
        List<ArchiveTestScoreVO> vos = new ArrayList<>();

        //查出这堂课所有的考试记录
        List<ArchiveTestScoreEntity> list = archiveTestScoreDao.selectTestScoreByCourseId(courseId);

        //查出课程下的所有考核点
        List<ArchiveWeightTargetAssessVO> archiveWeightTargetAssessVOS = archiveWeightTargetAssessDao.selectAssessByCourseId(courseId);
        //统计共有多少个测试点
        int size = 0;
        for (int i = 0; i < archiveWeightTargetAssessVOS.size(); i++) {
            Long assessId = archiveWeightTargetAssessVOS.get(i).getAssessId();
            //查出考核点下的所有测试点
            List<ArchiveWeightAssessTestVO> archiveWeightAssessTestVOS = archiveWeightAssessTestDao.selectTestName(assessId);
            size += archiveWeightAssessTestVOS.size();
        }
        //遍历所有考试记录
        for (int i = 0; i < list.size(); i+=size) {
            List<String> score = new ArrayList<>();
            ArchiveTestScoreVO vo = new ArchiveTestScoreVO();
            //遍历所有测试点
            for (int j = 0; j < size; j++) {
                vo.setStuId(list.get(i).getStuId());
                vo.setStuName(list.get(i).getStuName());
                score.set(j,list.get(i+j).getScore());
            }
            vo.setScore(score);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public void scoreImportExcel(MultipartFile file,Long courseId) {
        ExcelSyncDataListener<Map<Integer, String>> listener=new ExcelSyncDataListener<>();
        ExcelUtils.readSync(file,listener,0,0);
        List<Map<Integer, String>> list=listener.getList();
        List<Long> assessTestList=new ArrayList();
        int testLength=0;
        for(int i=2;i<list.get(0).size();i++){
            if(list.get(0).get(i)==null){
                break;
            }
            System.out.println(list.get(0).get(i));
            ArchiveWeightAssessTestEntity entity=new ArchiveWeightAssessTestEntity();
            entity.setCourseId(courseId);
            entity.setTestName(list.get(0).get(i));
            archiveWeightAssessTestDao.insertAssessTest(entity);
            System.out.println(entity.getId());
            assessTestList.add(entity.getId());
            testLength++;
        }
        for(int i=1;i<list.size();i++){
            String stuId=list.get(i).get(0);
            String stuName=list.get(i).get(1);
            System.out.println(stuId+" "+stuName);
            List<ArchiveTestScoreEntity> testScoreList=new ArrayList<>();
            for(int j=2;j<testLength+2;j++){
                ArchiveTestScoreEntity archiveTestScoreEntity=new ArchiveTestScoreEntity();
                archiveTestScoreEntity.setScore(list.get(i).get(j));
                archiveTestScoreEntity.setTestId(assessTestList.get(j-2));
                testScoreList.add(archiveTestScoreEntity);
            }
            System.out.println(testScoreList);
            archiveTestScoreDao.insertArchiveTestScore(stuId,stuName,testScoreList);
        }
    }


    @Override
    public int insertArchiveTestScore(String stuId, String stuName, List list) {
        return archiveTestScoreDao.insertArchiveTestScore(stuId,stuName,list);
    }

}
