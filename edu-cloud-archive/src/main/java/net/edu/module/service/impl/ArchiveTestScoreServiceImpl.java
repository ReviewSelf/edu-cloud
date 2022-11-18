package net.edu.module.service.impl;

import lombok.AllArgsConstructor;
import net.edu.framework.common.excel.ExcelSyncDataListener;
import net.edu.framework.common.utils.ExcelUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.ArchiveWeightAssessTestDao;
import net.edu.module.dao.ArchiveWeightGoalDao;
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
    @Autowired
    private ArchiveWeightGoalDao archiveWeightGoalDao;

    /**
     * 总评表
     * @param courseId
     * @return
     */
    @Override
    public List<ArchiveTestScoreVO> selectTestScoreByCourseId(Long courseId) {
        //所有学生的所有考试的成绩
        List<ArchiveTestScoreEntity> scoreEntities = archiveTestScoreDao.selectTestScoreByCourseId(courseId);
        List<Double> longs = archiveWeightAssessTestDao.selectTestByCourseId(courseId);
        int size = longs.size();
        List<ArchiveTestScoreVO> list = new ArrayList<>();
        for (int i = 0; i < scoreEntities.size(); i+=size) {
            double sumScore = 0;
            List<Double> score = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                score.add(Double.valueOf(scoreEntities.get(i+j).getScore()));
                sumScore+=Double.parseDouble(scoreEntities.get(i+j).getScore());
            }
            ArchiveTestScoreVO vo = new ArchiveTestScoreVO();
            vo.setStuId(scoreEntities.get(i).getStuId());
            vo.setStuName(scoreEntities.get(i).getStuName());
            vo.setScore(score);
            vo.setTotal(sumScore);
            list.add(vo);
        }

        return list;
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
