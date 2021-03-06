package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 读取application.properties文件，拼接测试接口URL
 */
public class ConfigFile {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application01", Locale.CHINA);
    ;

    //传进来的name，必须是InterfaceName类中枚举的接口名，
    public static String getUrl(InterfaceName name) {
        String address = bundle.getString("test.uri");
        String uri = ""; //获取application.perperties中的uri
        String testUrl;  //最终的测试地址，address+uri

        if (name == InterfaceName.LOGIN) {
            uri = bundle.getString("login.uri");
        }
        if (name == InterfaceName.ADDPRODUCT) {
            uri = bundle.getString("addproduct.uri");
        }
        if (name == InterfaceName.UPDATEPRODUCT) {
            uri = bundle.getString("updateproduct.uri");
        }
        if (name == InterfaceName.GETTICKET) {
            uri = bundle.getString("ticket.uri");
        }
        if (name == InterfaceName.LOOKPRODUCT){
            uri = bundle.getString("lookproduct.uri");
        }
        if (name == InterfaceName.LOOKWHITEPRODUCTLIST){
            uri = bundle.getString("lookwhiteProductlist.uri");
        }
        if (name == InterfaceName.LOOKWHITELIST){
            uri = bundle.getString("lookwhitelist.uri");
        }
        if (name == InterfaceName.LOOKWHITEENTERPRISELIST){
            uri = bundle.getString("lookwhiteentepriselist.uri");
        }
        if (name == InterfaceName.IMPORTWHITELIST){
            uri = bundle.getString("importwhitelist.uri");
        }
        if (name == InterfaceName.DOWNLOADTEMPLATE) {
            uri = bundle.getString("downloadTemplate.uri");
        }
        if (name == InterfaceName.PRODUCTSHELVES) {
            uri = bundle.getString("productshelves.uri");
        }
        if (name == InterfaceName.LOGINOUT) {
            uri = bundle.getString("logout.uri");
        }
        if (name == InterfaceName.ADDORUPDATE) {
            uri = bundle.getString("addOrUpdate.uri");
        }

        testUrl = address + uri;
        return testUrl;
    }
    public static String getParams(){
        String params = bundle.getString("param.uri");
        return params;
    }
}