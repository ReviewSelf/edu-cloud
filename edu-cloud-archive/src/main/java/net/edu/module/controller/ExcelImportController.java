package net.edu.module.controller;



import net.edu.framework.common.excel.ExcelSyncDataListener;
import net.edu.framework.common.utils.ExcelUtils;
import net.edu.module.utils.ExcelImportScoreUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ExcelImportController {
    public static void main(String[] args) {
        String fileName = "E:\\home" + File.separator + "archiveImportExcel.xlsx";
        ExcelSyncDataListener<Map<Integer, String>> listener=new ExcelSyncDataListener<>();
        ExcelUtils.readSync(new File(fileName),listener,0,0);
        List<Map<Integer, String>> list=listener.getList();
        ExcelImportScoreUtil.ExcelImportScoreUtil(list);
    }
}
