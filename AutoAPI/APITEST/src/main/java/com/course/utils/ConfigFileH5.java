package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 读取application.properties文件，拼接测试接口URL
 */
public class ConfigFileH5 {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application02", Locale.CHINA);
    ;

    //传进来的name，必须是InterfaceName类中枚举的接口名，
    public static String getUrl(InterfaceName name) {
        String address = bundle.getString("test.uri");
        String uri = ""; //获取application.perperties中的uri
        String testUrl;  //最终的测试地址，address+uri

        if (name == InterfaceName.H5LOGIN) {
            uri = bundle.getString("login.uri");
        }

        testUrl = address + uri;
        return testUrl;
    }
}