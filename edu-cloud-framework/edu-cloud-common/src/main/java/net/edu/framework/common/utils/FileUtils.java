package net.edu.framework.common.utils;

import cn.hutool.core.io.FileUtil;

import java.io.File;

/**
 *
 */
public class FileUtils {

    //创建文件夹
    public static String mkDir(String dir){
        File file =new File(dir);
        if  (!file.exists()  && !file.isDirectory()) {
            file.mkdir();
        }
        return dir;
    }


}
