package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.ProblemCodeSampleEntity;
import net.edu.module.query.ProblemCodeSampleQuery;
import net.edu.module.vo.CodeProblemVO;
import net.edu.module.vo.FileUploadVO;
import net.edu.module.vo.ProblemCodeSampleVO;
import net.edu.module.vo.SampleVO;

import java.util.List;

/**
 * 测试样例表
 *
 * @author sqw 
 * @since 1.0.0 2022-09-07
 */
public interface ProblemCodeSampleService extends BaseService<ProblemCodeSampleEntity> {

    PageResult<ProblemCodeSampleVO> page(ProblemCodeSampleQuery query);

    void save(ProblemCodeSampleVO vo);

    void update(ProblemCodeSampleVO vo);

    void delete(List<Long> idList);

    CodeProblemVO getProblem(Long id);

    void saveSample(List<SampleVO> sampleVOS,Long problemId);
}