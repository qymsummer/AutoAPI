package com.course.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/13 17:29
 */


    public class mytest {

    public static void main(String[] args) throws IOException {
        String loadPath = "E:\\RuanJian\\xx.txt", outPath = "E:\\RuanJian\\xx.txt";
        InputStream in = new FileInputStream(new File(loadPath));
        FileOutputStream out = new FileOutputStream(new File(outPath));
        byte[] buffer = new byte[100];// 缓存大小
        int readNumber = 0;
        while ((readNumber = in.read(buffer)) != -1) {
            out.write(buffer, 0, readNumber);// 读取并输出buffer数组里面0~n个字节
        }
        in.close();

        System.out.println(in);
        System.out.println(out);
    }

}

