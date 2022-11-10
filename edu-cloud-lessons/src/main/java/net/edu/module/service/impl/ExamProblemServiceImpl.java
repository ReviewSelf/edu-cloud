package net.edu.module.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.framework.security.user.SecurityUser;
import net.edu.module.api.EduProblemApi;
import net.edu.module.api.EduTeachApi;
import net.edu.module.convert.ExamProblemConvert;
import net.edu.module.dao.ExamProblemDao;
import net.edu.module.entity.ExamProblemEntity;
import net.edu.module.query.ExamProblemQuery;
import net.edu.module.service.ExamProblemService;
import net.edu.module.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * 课堂练习表
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-15
 */
@Service
@AllArgsConstructor
public class ExamProblemServiceImpl extends BaseServiceImpl<ExamProblemDao, ExamProblemEntity> implements ExamProblemService {

    private final EduProblemApi eduProblemApi;

    private final ExamProblemDao examProblemDao;

    private final RedisUtils redisUtils;

    @Override
    public PageResult<ExamProblemVO> page(ExamProblemQuery query) {
        Page<ExamProblemVO> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ExamProblemVO> list = baseMapper.page(page, query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }

    @Override
    public List<ExamProblemEntity> list(Long examId) {
        List<ExamProblemEntity> list = null;
        list = (List<ExamProblemEntity>) redisUtils.get(RedisKeys.getExamProblem(examId));
        if (list == null) {
            list = new LambdaQueryChainWrapper<>(baseMapper)
                    .eq(ExamProblemEntity::getExamId, examId).orderByAsc(ExamProblemEntity::getScore).list();
            redisUtils.set(RedisKeys.getExamProblem(examId), list, RedisUtils.HOUR_ONE_EXPIRE);
        }
        return list;
    }


    @Override
    public void copyFromPaper(Long paperId, Long examId) {
        List<ProblemPaperItemEntity> problemList = eduProblemApi.getPaperProblem(paperId).getData();
        if (!CollUtil.isEmpty(problemList)) {
            //insert
            examProblemDao.insertExamProblemFromPaper(problemList, examId);
        }

    }


}
