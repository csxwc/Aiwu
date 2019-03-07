package com.aiwu.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class HadoopTool {

    private static Logger logger = Logger.getLogger("this.class");

    private static FileSystem getFileSystem() {

        FileSystem fileSystem = null;
        System.setProperty("hadoop.home.dir", "/home/hong/Downloads/hadoop-2.7.7");
        Configuration conf = new Configuration();
        conf.set("fs.defaultFs", "hdfs://192.168.101.18:9000");

        try {
            fileSystem = FileSystem.get(new URI("hdfs://192.168.101.18:9000"),conf, "root");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return fileSystem;

    }

    /**
     * 创建文件夹
     */
    public static void mkdirFolder(String folderPath) {
        try {
            FileSystem fs = getFileSystem();
            fs.mkdirs(new Path(folderPath));
            logger.info("创建文件夹成功："+folderPath);
        } catch (Exception e) {
            logger.error("创建失败"+e.getMessage());
        }

    }


    /**
     * 上传文件到hdfs
     *
     * @param localFolderPath 本地目录
     * @param fileName        文件名
     * @param hdfsFolderPath  上传到hdfs的目录
     */
    public static void uploadFile(String localFolderPath, String fileName, String hdfsFolderPath) {
        FileSystem fs = getFileSystem();
        try {
            InputStream in = new FileInputStream(localFolderPath + fileName);
            OutputStream out = fs.create(new Path(hdfsFolderPath + fileName));
            IOUtils.copyBytes(in, out, 4096, true);
            logger.info("上传文件成功："+fileName);
        } catch (Exception e) {
            logger.error("上传文件失败"+e.getMessage());
            System.out.println("上传文件失败");
        }
    }


    /**
     * 从hdfs获取文件
     *
     * @param downloadPath     hdfs的路径
     * @param downloadFileName hdfs文件名
     * @param savePath         保存的本地路径
     */
    public static void getFileFromHadoop(String downloadPath, String downloadFileName, String savePath) {
        FileSystem fs = getFileSystem();
        try {
            InputStream in = fs.open(new Path(downloadPath + downloadFileName));
            OutputStream out = new FileOutputStream(savePath + downloadFileName);
            IOUtils.copyBytes(in, out, 4096, true);
            logger.info("下载文件成功：" + savePath + downloadFileName);
        }  catch (Exception e) {
            logger.error("获取文件失败"+e.getMessage());
        }
    }

    /**
     * 删除文件
     * delete(path,boolean)
     * boolean如果为true，将进行递归删除，子目录及文件都会删除
     * false 只删除当前

     */
    public static void deleteFile(String deleteFilePath) {
        FileSystem fs = getFileSystem();
        //要删除的文件路径
        try {
            Boolean deleteResult = fs.delete(new Path(deleteFilePath), true);
            logger.info("删除文件成功：" + deleteFilePath);
        } catch (Exception e) {
            logger.error("删除文件失败"+e.getMessage());
        }
    }

    /**
     * 日志打印文件内容
     * @param filePath 文件路径
     */
    public static void readOutFile(String filePath) {
        try {
            InputStream inputStream = getFileSystem().open(new Path(filePath));
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "GB2312"));//防止中文乱码
            String line = null;
            logger.info("文件 " + filePath + " 内容如下：");
            while ((line = bf.readLine()) != null) {
                logger.info(line);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {

        uploadFile("/home/hong/Downloads/", "actionList.txt", "/input/");

    }
}
