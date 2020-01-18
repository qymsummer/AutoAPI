package com.course.bvtcase.filemanagement;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/14 11:02
 * @author qym
 */
public class FileTest {

    public static void main(String[] args) {


        String filePath = "E:\\RuanJian\\ID.txt";
        byte[] buff = new byte[1024];
        int i = 0;
        try (FileInputStream fis = new FileInputStream(filePath)) {
            // i的目的在于防止最后一次读取的字节小于b长度,否则会自动被填充0
            if ((i = fis.read(buff)) != -1) {
                System.out.println(new String(buff, 0, i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
