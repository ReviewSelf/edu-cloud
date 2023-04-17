package net.edu.module.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.common.excel.ExcelSyncDataListener;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.ExcelUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.module.convert.ArchiveSignConvert;
import net.edu.module.dao.ArchiveSignDao;
import net.edu.module.entity.ArchiveSignEntity;
import net.edu.module.query.ArchiveSignQuery;
import net.edu.module.service.ArchiveSignService;
import net.edu.module.vo.ArchiveSignVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ArchiveSignServiceImpl extends BaseServiceImpl<ArchiveSignDao, ArchiveSignEntity> implements ArchiveSignService {

    @Autowired
    private ArchiveSignDao archiveSignDao;


    @Override
    public void signImportExcel(MultipartFile file, Long bookId) {
        ExcelSyncDataListener<Map<Integer, String>> listener=new ExcelSyncDataListener<>();
        ExcelUtils.readSync(file,listener,1,0);
        List<Map<Integer, String>> list=listener.getList();
//        System.out.println(list);
        List<ArchiveSignEntity> archiveSignEntityList=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            ArchiveSignEntity archiveSignEntity=new ArchiveSignEntity();
            archiveSignEntity.setBookId(bookId);
            String stuId=list.get(i).get(0);
            String stuName=list.get(i).get(1);
            archiveSignEntity.setStuId(stuId);
            archiveSignEntity.setStuName(stuName);
            List signList=new ArrayList();
            for(int j=2;j<list.get(i).size();j++){
                signList.add(list.get(i).get(j));
            }
            String record = signList.toString();
            archiveSignEntity.setRecord(record);
            archiveSignEntityList.add(archiveSignEntity);
        }
        System.out.println(archiveSignEntityList);
        archiveSignDao.insertArchiveSignDao(archiveSignEntityList);
    }

    @Override
    public PageResult<ArchiveSignVO> page(ArchiveSignQuery query) {
        QueryWrapper<ArchiveSignEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("book_id", query.getBookId());
        IPage<ArchiveSignEntity> page =baseMapper.selectPage(getPage(query),wrapper);
//        IPage<ArchiveSignEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(ArchiveSignConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private LambdaQueryWrapper<ArchiveSignEntity> getWrapper(ArchiveSignQuery query){
        LambdaQueryWrapper<ArchiveSignEntity> wrapper = Wrappers.lambdaQuery();

        return wrapper;
    }

    @Override
    public void save(ArchiveSignVO vo) {
        ArchiveSignEntity entity = ArchiveSignConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
    }

    @Override
    public void update(ArchiveSignVO vo) {
        ArchiveSignEntity entity = ArchiveSignConvert.INSTANCE.convert(vo);

        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }
}
