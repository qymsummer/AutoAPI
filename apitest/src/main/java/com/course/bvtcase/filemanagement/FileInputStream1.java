package com.course.bvtcase.filemanagement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/14 10:51
 */
public class FileInputStream1 {
    private static final int SIZE = 4096;

    public static void main(String[] args) throws IOException {

        File file = new File("E:\\xxx.txt");
        if(!file.exists()){
            throw new RuntimeException("要读取的文件不存在");
        }


        FileInputStream fis = new FileInputStream(file);
        System.out.println(file);
        System.out.println("--------");
        System.out.println(fis);
        /*
        int len = 0;
        byte[] buf = new byte[SIZE];
        while((len=fis.read(buf))!=-1){
            System.out.println(new String(buf,0,len));
        }

         */

        //关资源
        fis.close();
    }
}
