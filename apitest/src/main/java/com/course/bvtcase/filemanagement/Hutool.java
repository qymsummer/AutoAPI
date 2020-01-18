package com.course.bvtcase.filemanagement;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.lang.Console;

import java.io.File;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/14 10:33
 * @author qym
 */
public class Hutool {
    public static void main(String[] args) {
        File file = FileUtil.file("E:\\RuanJian\\ID.txt");
        String type = FileTypeUtil.getType(file);
        Console.log(type);
        System.out.println(file);
    }
}
