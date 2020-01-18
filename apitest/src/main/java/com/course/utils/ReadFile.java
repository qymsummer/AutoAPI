package com.course.utils;
import java.io.*;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/13 14:14
 * @author qym
 */
public class ReadFile {
    public static InputStream readFile(String loadPath) throws IOException {
        InputStream in = new FileInputStream(new File(loadPath));
        in.close();
        return in;
    }
}
