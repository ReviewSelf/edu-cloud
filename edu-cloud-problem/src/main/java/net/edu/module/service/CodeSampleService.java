package net.edu.module.service;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.BaseService;
import net.edu.module.entity.CodeSampleEntity;
import net.edu.module.query.CodeSampleQuery;
import net.edu.module.vo.CodeSampleVO;
import net.edu.module.vo.SampleVO;

import java.util.List;

/**
 * 测试样例表
 *
 * @author sqw 
 * @since 1.0.0 2022-09-07
 */
public interface CodeSampleService extends BaseService<CodeSampleEntity> {

    PageResult<CodeSampleVO> page(CodeSampleQuery query);


    void update(CodeSampleVO vo);

    void delete(List<Long> idList);


    void saveSample(List<SampleVO> sampleVOS,Long problemId);
}