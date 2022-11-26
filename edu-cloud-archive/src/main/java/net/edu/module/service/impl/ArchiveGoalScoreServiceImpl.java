package net.edu.module.service.impl;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.dao.*;
import net.edu.module.entity.ArchiveGoalScoreEntity;
import net.edu.module.service.ArchiveGoalScoreService;
import net.edu.module.utils.CalculateProportionUtil;
import net.edu.module.vo.ArchiveGoalScoreVO;
import net.edu.module.vo.ArchiveScoreBookClassInfoVO;
import net.edu.module.vo.ArchiveSignVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考试成绩表
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-16
 */
@Service
@AllArgsConstructor
public class ArchiveGoalScoreServiceImpl extends BaseServiceImpl<ArchiveGoalScoreDao, ArchiveGoalScoreEntity> implements ArchiveGoalScoreService {

    @Autowired
    private ArchiveSignDao archiveSignDao;
    @Autowired
    private ArchiveGoalScoreDao goalScoreDao;
    @Override
    public ArchiveGoalScoreVO getGradeInfo(JSONObject classInfo, String id){
        ArchiveGoalScoreVO archiveGoalScoreVO=new ArchiveGoalScoreVO();
        archiveGoalScoreVO.setMajorName(String.valueOf(classInfo.get("majorName")));
        archiveGoalScoreVO.setClassName(String.valueOf(classInfo.get("class")));
        List<ArchiveSignVO> archiveSignVO= archiveSignDao.getSignByBookId(id);
        int TargetNum=archiveSignVO.size();
        int actualNUm=0;
        int absentNum=0;
        String courseId= String.valueOf(classInfo.get("courseId"));
        archiveGoalScoreVO.setTargetNum(String.valueOf(TargetNum));
        List<ArchiveGoalScoreEntity> List= goalScoreDao.selectByStuId(archiveSignVO,courseId);
        actualNUm=List.size();
        absentNum=TargetNum-actualNUm;
        archiveGoalScoreVO.setAbsentNum(String.valueOf(absentNum));
        archiveGoalScoreVO.setActualNum(String.valueOf(actualNUm));
        int excellent=0;
        String excellentPercent;
        int  good=0;
        String  goodPercent;
        int  medium=0;
        String  mediunPercent;
        int  pass=0;
        String passPercent;
        int  fail=0;
        String failPercent;
        for (ArchiveGoalScoreEntity item : List){
            if(item.getTotal()>=90){
                excellent++;
            } else if (item.getTotal()>=80) {
                good++;
            } else if (item.getTotal()>=70) {
                medium++;
            } else if (item.getTotal()>=60) {
                pass++;
            }else {
                fail++;
            }
        }
        excellentPercent=String.valueOf(CalculateProportionUtil.proportionInt(excellent,TargetNum));
        goodPercent =String.valueOf(CalculateProportionUtil.proportionInt(good,TargetNum));
        mediunPercent=String.valueOf(CalculateProportionUtil.proportionInt(medium,TargetNum));
        passPercent=String.valueOf(CalculateProportionUtil.proportionInt(pass,TargetNum));
        failPercent=String.valueOf(CalculateProportionUtil.proportionInt(fail,TargetNum));
        archiveGoalScoreVO.setExcellent(String.valueOf(excellent));
        archiveGoalScoreVO.setExcellentPercent(excellentPercent);
        archiveGoalScoreVO.setGood(String.valueOf(good));
        archiveGoalScoreVO.setGoodPercent(goodPercent);
        archiveGoalScoreVO.setMedium(String.valueOf(medium));
        archiveGoalScoreVO.setMediumPercent(mediunPercent);
        archiveGoalScoreVO.setPass(String.valueOf(pass));
        archiveGoalScoreVO.setPassPercent(passPercent);
        archiveGoalScoreVO.setFail(String.valueOf(fail));
        archiveGoalScoreVO.setFailPercent(failPercent);
        return archiveGoalScoreVO;
    }


}
