package net.edu.module.controller;



import com.alibaba.excel.EasyExcel;
import net.edu.module.untils.NoModelDataListener;

import java.io.File;

public class ExcelImportController {
    public static void main(String[] args) {
        String fileName = "E:\\home" + File.separator + "test.xlsx";
        EasyExcel.read(fileName, new NoModelDataListener()).sheet().doRead();
    }
}
