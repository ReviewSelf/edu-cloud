package net.edu.module.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;

import net.edu.framework.common.page.PageResult;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveScoreBookConvert;
import net.edu.module.dao.ArchiveScoreBookDao;
import net.edu.module.query.ArchiveScoreBookQuery;
import net.edu.module.service.ArchiveScoreBookService;
import net.edu.module.vo.ArchiveScoreBookClassInfoVO;
import net.edu.module.vo.ArchiveScoreBookClassTableVO;
import net.edu.module.vo.ArchiveScoreBookVO;
import net.maku.entity.ArchiveScoreBookEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 记分册
 *
 * @author 阿沐 babamu@126.com
 * @since 1.0.0 2022-11-22
 */
@Service
@AllArgsConstructor
public class ArchiveScoreBookServiceImpl extends BaseServiceImpl<ArchiveScoreBookDao, ArchiveScoreBookEntity> implements ArchiveScoreBookService {

    @Autowired
    private ArchiveScoreBookDao archiveScoreBookDao;

    @Override
    public PageResult<ArchiveScoreBookVO> page(ArchiveScoreBookQuery query) {
        IPage<ArchiveScoreBookEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(ArchiveScoreBookConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<ArchiveScoreBookEntity> getWrapper(ArchiveScoreBookQuery query){
        LambdaQueryWrapper<ArchiveScoreBookEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(ArchiveScoreBookVO vo) {
        ArchiveScoreBookEntity entity = ArchiveScoreBookConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ArchiveScoreBookVO vo) {
        ArchiveScoreBookEntity entity = ArchiveScoreBookConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public void  InsertClassInfo(ArchiveScoreBookClassInfoVO vo){
        archiveScoreBookDao.InsertClassInfo(vo);

    }


    @Override
    public List<ArchiveScoreBookClassTableVO> getClassTable(String id){

        List<ArchiveScoreBookVO> list=archiveScoreBookDao.selectList(id);
        JSONArray jsonArray = JSONUtil.parseArray(list.get(0).getClassSchedule());
        List<ArchiveScoreBookClassTableVO> archiveScoreBookClassTableVOList=new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject=JSONUtil.parseObj(jsonArray.get(i));
            ArchiveScoreBookClassTableVO archiveScoreBookClassTableVO=new ArchiveScoreBookClassTableVO();
            archiveScoreBookClassTableVO.setId(String.valueOf(i));
            archiveScoreBookClassTableVO.setTime(String.valueOf(jsonObject.get("time")));
            archiveScoreBookClassTableVO.setPlace(String.valueOf(jsonObject.get("place")));
            archiveScoreBookClassTableVOList.add(archiveScoreBookClassTableVO);
        }
        return archiveScoreBookClassTableVOList;
    }

    @Override
    public void deleteClassTable(String id,String deleteId){
        List<ArchiveScoreBookVO> list=archiveScoreBookDao.selectList(id);
        JSONArray jsonArray=JSONUtil.parseArray(list.get(0).getClassSchedule());
        jsonArray.remove(Integer.parseInt(deleteId));
        String  classSchedule=JSONUtil.toJsonStr(jsonArray);
        System.out.println(jsonArray);
        System.out.println(classSchedule);
        archiveScoreBookDao.updateByDeleteId(id,classSchedule);
    }

    @Override
    public void  updateClassTable(String id,Object dataForm){
        String classSchedule=dataForm.toString();
        System.out.println(classSchedule);
        archiveScoreBookDao.updateByDeleteId(id,classSchedule);
    }
}
