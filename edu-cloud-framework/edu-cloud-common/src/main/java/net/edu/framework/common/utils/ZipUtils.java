package net.edu.framework.common.utils;


import net.edu.framework.common.exception.ServerException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author 马佳浩
 * @date 2022/11/25 15:53:08
 * @description
 */
public class ZipUtils {

    // UTF-8 字符集
    private static final String UTF8 = "UTF-8";

    // GBK
    private static final String GBK = "GBK";

    // 文件类型 <application/zip>
    private static final String CONTENT_TYPE_ZIP = "application/zip";

    // 文件类型 <application/x-zip-compressed>
    private static final String CONTENT_TYPE_ZIP_COMPRESSED = "application/x-zip-compressed";


    public static void checkZipFileParam(MultipartFile zipFile) {
        if (Objects.isNull(zipFile)) {
            throw new ServerException("【导入Zip文件】缺少Zip包");
        }
        String contentType = zipFile.getContentType();
        if (!CONTENT_TYPE_ZIP.equals(contentType) && !CONTENT_TYPE_ZIP_COMPRESSED.equals(contentType)) {
            throw new ServerException("【导入Zip文件】缺少Zip包");
        }
    }


    /**
     * 解压
     *
     * @param zippath
     * @param resourcepath
     */
    public static void unzip(String zippath, String resourcepath) {
        //判断生成目录是否生成，如果没有就创建
        FileUtils.mkDir(resourcepath);
        ZipFile zp = null;
        try {
            //指定编码，否则压缩包里面不能有中文目录
            zp = new ZipFile(zippath, Charset.forName("gbk"));
            //遍历里面的文件及文件夹
            Enumeration entries = zp.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zp.getInputStream(entry);
                String outpath = (resourcepath + zipEntryName).replace("/", File.separator);
                //判断路径是否存在，不存在则创建文件路径
                File file = new File(outpath.substring(0, outpath.lastIndexOf(File.separator)));
                if (!file.exists()) {
                    file.mkdirs();
                }
                //判断文件全路径是否为文件夹,如果是,不需要解压
                if (new File(outpath).isDirectory()) {
                    continue;
                }
                OutputStream out = new FileOutputStream(outpath);
                byte[] bf = new byte[2048];
                int len;
                while ((len = in.read(bf)) > 0) {
                    out.write(bf, 0, len);
                }
                in.close();
                out.close();
            }

            zp.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //删除文件zip
//            zp.getEntry();
        }
    }

    //这里path指的是读取的zip文件路径
    public static void getFileName(String path) {
        List<String> fileNames = new ArrayList<>();
        try {
            //这里一定要带入格式，不是在读取zip文件的时候会存在问题
            ZipFile zipFile = new ZipFile(path, Charset.forName("gbk"));
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                String fileName = entries.nextElement().getName();
                fileNames.add(fileName);
                System.out.println("文件名称： " + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将多个文件压缩
     *
     * @param fileList
     * @param zipFileName
     * @throws IOException
     */
    public static void zip(List<File> fileList, String zipFileName) throws IOException {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        ZipOutputStream zipOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        fileOutputStream = new FileOutputStream(new File("D:/zip/" + zipFileName+".zip"));
        zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
        //创建读写缓冲区
        byte[] bufs = new byte[1024 * 10];

        for (File file : fileList) {
            if (null == file || 0 == file.length() || !file.exists()){
                return;
            }
            //创建zip实体，并添加进压缩包
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zipOutputStream.putNextEntry(zipEntry);

            //读取待压缩的文件并写进压缩包
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream, 1024 * 10);
            int read = 0;
            while ((read = bufferedInputStream.read(bufs, 0, 1024 * 10)) != -1) {
                zipOutputStream.write(bufs, 0, read);
            }
        }

        if (bufferedInputStream != null) {
            bufferedInputStream.close();
        }
        if (zipOutputStream != null) {
            zipOutputStream.close();
        }


    }

    /**
     * 下载zip文件
     * @param response
     * @param zipFileName
     * @throws IOException
     */

    public static void downloadZip(HttpServletResponse response,String zipFileName) throws IOException {
        File zipFile = new File("D:/zip/"+zipFileName+".zip");
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=" + zipFileName+".zip");

        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;

        outputStream =response.getOutputStream();
        fileInputStream = new FileInputStream(zipFile);

        byte[] bufs = new byte[1024 * 10];
        int read = 0;
        while ((read = fileInputStream.read(bufs, 0, 1024 * 10)) != -1) {
            outputStream.write(bufs, 0, read);
        }
        fileInputStream.close();
        outputStream.close();

        File file = new File("D:/zip/"+zipFileName);
        file.delete();

        if (fileInputStream != null){
            fileInputStream.close();
        }
        if (outputStream != null){
            outputStream.close();
        }
    }


}
