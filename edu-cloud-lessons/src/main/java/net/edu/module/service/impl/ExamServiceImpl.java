package net.edu.module.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.constant.Constant;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.common.utils.TreeUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.convert.ExamConvert;
import net.edu.module.dao.ExamDao;
import net.edu.module.entity.ExamEntity;
import net.edu.module.query.ExamQuery;
import net.edu.module.service.ExamAttendLogService;
import net.edu.module.service.ExamProblemService;
import net.edu.module.service.ExamService;
import net.edu.module.vo.ExamAttendLogVO;
import net.edu.module.vo.ExamPaperVo;
import net.edu.module.vo.ExamVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 考试
 *
 * @author 小樊 babamu@126.com
 * @since 1.0.0 2022-10-09
 */
@Service
@AllArgsConstructor
public class ExamServiceImpl extends BaseServiceImpl<ExamDao, ExamEntity> implements ExamService {


    private final RedisUtils redisUtils;

    private final ExamAttendLogService examAttendLogService;

    private final ExamProblemService examProblemService;




    private final ExamDao examDao;

    @Override
    public PageResult<ExamVO> page(ExamQuery query) {
        query.setTeacherId(SecurityUser.getUserId());
        Page<ExamVO> page = new Page<>(query.getPage(),query.getLimit());
        IPage<ExamVO> list = baseMapper.page(page,query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }


    @Override
    public ExamEntity get(Long examId) {
        ExamEntity entity =baseMapper.selectById(examId);
        return entity;
    }



    @Override
    public PageResult<ExamVO> studentPage(ExamQuery query){
        Page<ExamVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ExamVO> list = baseMapper.studentPage(page, query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    @Override
    public List<ExamVO> getExamingList(Long userId){
        List<ExamVO> list = baseMapper.getExamingList(userId);
        return list;
    }

    @Override
    public ExamVO getPaper(Long paperId){
        ExamVO examVO = examDao.selectPaperManage(paperId);
        System.out.println(examVO);
        return examVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ExamVO vo) {
        ExamEntity entity = ExamConvert.INSTANCE.convert(vo);

         baseMapper.insert(entity);

        //插入题目
        examProblemService.copyFromPaper(vo.getPaperId(),entity.getId());

        //插入名单
        examAttendLogService.copyFromClass(vo.getClassId(),entity.getId());




    }

    @Override
    public void update(ExamVO vo) {
        ExamEntity entity = ExamConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }


    @Override
    public void updateExamIndex(Long examId) {
        Long userId = SecurityUser.getUserId();
        ExamPaperVo vo= (ExamPaperVo) redisUtils.get(RedisKeys.getStuExam(examId,userId));
        if (vo.getPaperProblem().size() >vo.getProblemIndex()){
            vo.setProblemIndex(vo.getProblemIndex()+1);
            Long time=vo.getAttendLogVO().getFinishExamTime().getTime()-System.currentTimeMillis()+5000L;
            redisUtils.set(RedisKeys.getStuExam(examId,userId),vo,time/1000);
        }



    }

    @Override
    public void submitPaper(Long examId) {
        Long userId = SecurityUser.getUserId();
        examAttendLogService.updateExamStatus(2,examId,userId,new Date());
        redisUtils.del(RedisKeys.getStuExam(examId,userId));
    }


}