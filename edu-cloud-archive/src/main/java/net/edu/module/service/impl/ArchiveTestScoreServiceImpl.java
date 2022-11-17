package net.edu.module.service.impl;

import net.edu.module.dao.ArchiveTestScoreDao;
import net.edu.module.service.ArchiveTestScoreService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArchiveTestScoreServiceImpl implements ArchiveTestScoreService {

    @Autowired
    private ArchiveTestScoreDao archiveTestScoreDao;

    @Override
    public int insertArchiveTestScore(String stuId, String stuName, List list) {
        return archiveTestScoreDao.insertArchiveTestScore(stuId,stuName,list);
    }
}
