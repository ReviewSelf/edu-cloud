package net.edu.module.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.exception.ServerException;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.*;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.api.EduFileApi;
import net.edu.module.convert.CodeProblemConvert;
import net.edu.module.entity.CodeProblemEntity;
import net.edu.module.query.CodeProblemQuery;
import net.edu.module.service.CodeSampleService;
import net.edu.module.vo.CodeProblemAnswerVo;
import net.edu.module.vo.CodeProblemVO;
import net.edu.module.dao.CodeProblemDao;
import net.edu.module.service.CodeProblemService;
import net.edu.module.vo.CodeSampleVO;
import net.edu.module.vo.FileUploadVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 代码题库表
 *
 * @author 马佳浩
 * @since 1.0.0 2022-09-05
 */
@Service
@AllArgsConstructor
public class CodeProblemServiceImpl extends BaseServiceImpl<CodeProblemDao, CodeProblemEntity> implements CodeProblemService {


    private final EduFileApi eduFileApi;
    private final RedisUtils redisUtils;

    private final CodeSampleService codeSampleService;

    @Override
    public PageResult<CodeProblemVO> page(CodeProblemQuery query) {
        Page<CodeProblemVO> page = new Page<>(query.getPage(),query.getLimit());
        IPage<CodeProblemVO> list = baseMapper.page(page,query);
        return new PageResult<>(list.getRecords(), list.getTotal());
    }


    @Override
    public void save(CodeProblemVO vo) {
        CodeProblemEntity entity = CodeProblemConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(CodeProblemVO vo) {
        CodeProblemEntity entity = CodeProblemConvert.INSTANCE.convert(vo);

        updateById(entity);
        redisUtils.del(RedisKeys.getProblemInfo(vo.getId(),"code"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public void updateStatus(Long problemId) {
        baseMapper.updateStatus(problemId);
    }

    @Override
    public void updateUsedNum(Long id) {
        baseMapper.updateUsedNum(id);

    }

    @Override
    public void updateSubmitTimes(Long id, Boolean isTrue) {
         baseMapper.updateSubmitTimes(id,isTrue);
    }


    /**
     * 答题显示内容，每次缓存10分钟，10分钟一更新提交次数
     * @param problemId 问题ID
     * @return 代码题对象
     */
    @Override
    public CodeProblemVO getCodeProblemInfo(Long problemId) {
        CodeProblemVO codeProblemVO= (CodeProblemVO) redisUtils.get(RedisKeys.getProblemInfo(problemId,"code"));
        if(codeProblemVO==null){
            codeProblemVO=baseMapper.selectCodeProblemInfo(problemId);
            redisUtils.set(RedisKeys.getProblemInfo(problemId,"code"),codeProblemVO,RedisUtils.MIN_TEN_EXPIRE);
        }
        return codeProblemVO;
    }

    @Override
    public CodeProblemAnswerVo getCodeProblemAnswer(Long problemId) {
        CodeProblemEntity entity = baseMapper.selectById(problemId);
        CodeProblemAnswerVo vo = new CodeProblemAnswerVo();
        vo.setAnswer(entity.getAnswer());
        List<CodeSampleVO> codeSampleVOList = codeSampleService.getList(problemId);
        vo.setCodeSampleVOList(codeSampleVOList);
        return vo;
    }

    @SneakyThrows
    @Override
    public void importFromExcel(MultipartFile file) {
        List<CodeProblemVO> list=ExcelUtils.readSync(file,CodeProblemVO.class,3,0, ExcelUtils.getExcelFileType(file));
        if(list!=null){
            for (CodeProblemVO vo:list){
                vo.setMemoryLimit(vo.getMemoryLimit()* 1024);
                if(vo.getMemoryLimit()<2048) vo.setMemoryLimit(2048);
                save(vo);
            }
        }
    }

    @Override
    public Result<String> importZipFile(MultipartFile zipFile) {
        // 1.校验入参
        ZipUtils.checkZipFileParam(zipFile);

        // 2.上传至服务器路径下
        Result<FileUploadVO> uploadFileResultVo=eduFileApi.upload2(zipFile);
        if (uploadFileResultVo.getCode()!=0){
            throw new ServerException("文件上传失败");
        }

        ZipUtils.getFileName(uploadFileResultVo.getData().getUrl());
        // 3.解压Zip文件
        ZipUtils.unzip(uploadFileResultVo.getData().getUrl(),"E:/upload");


        return null;
    }
}
