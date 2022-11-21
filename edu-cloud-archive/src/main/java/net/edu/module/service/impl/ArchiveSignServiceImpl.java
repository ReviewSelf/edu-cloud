package net.edu.module.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.nacos.shaded.com.google.gson.Gson;
import lombok.AllArgsConstructor;
import net.edu.framework.common.excel.ExcelSyncDataListener;
import net.edu.framework.common.utils.ExcelUtils;
import net.edu.module.dao.ArchiveSignDao;
import net.edu.module.dao.ArchiveTestScoreDao;
import net.edu.module.entity.ArchiveSignEntity;
import net.edu.module.service.ArchiveSignService;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ArchiveSignServiceImpl implements ArchiveSignService {

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
}
