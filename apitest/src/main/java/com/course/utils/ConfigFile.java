package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/9 11:20
 */

public class ConfigFile {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);
    ;

    //传进来的name，必须是InterfaceName类中枚举的接口名，
    public static String getUrl(InterfaceName name) {
        String address = bundle.getString("test.uri");
        String uri = ""; //获取application.perperties中的uri
        String testUrl;  //最终的测试地址，address+uri

        if (name == InterfaceName.LOGIN) {
            uri = bundle.getString("login.uri");
        }
        if (name == InterfaceName.GETTICKET) {
            uri = bundle.getString("ticket.uri");
        }
        if (name == InterfaceName.ADDORUPDATE) {
            uri = bundle.getString("addOrUpdate.uri");
        }
        if (name == InterfaceName.FINDORG) {
            uri = bundle.getString("findOrg.uri");
        }
        if (name == InterfaceName.ADDORGNAME) {
            uri = bundle.getString("addOrgName.uri");
        }
        if (name == InterfaceName.FINDORGNAME) {
            uri = bundle.getString("findOrgName.uri");
        }
        if (name == InterfaceName.ADDORGPRODUCTAUTH) {
            uri = bundle.getString("updatePlatform.uri");
        }
        if (name == InterfaceName.FINDORGPRODUCTAUTH) {
            uri = bundle.getString("listForOrgPlatform.uri");
        }
        if (name == InterfaceName.ADDROLEORUPDATE) {
            uri = bundle.getString("addRoleOrUpdate.uri");
        }
        if (name == InterfaceName.FINDROLELIST) {
            uri = bundle.getString("findRoleList.uri");
        }
        if (name == InterfaceName.ADDROLEMENU) {
            uri = bundle.getString("addRoleMenu.uri");
        }
        if (name == InterfaceName.ADDGRAYSETTING) {
            uri = bundle.getString("addGraySetting.uri");
        }
        if (name == InterfaceName.FINDGRAYORG) {
            uri = bundle.getString("findGrayOrg.uri");
        }
        if (name == InterfaceName.ADDUSER) {
            uri = bundle.getString("addUser.uri");
        }
        if (name == InterfaceName.FINDUSER) {
            uri = bundle.getString("findUser.uri");
        }
        if (name == InterfaceName.INITPASSWORD) {
            uri = bundle.getString("initPassword.uri");
        }
        if (name == InterfaceName.ADDMENUUPDATE) {
            uri = bundle.getString("addMenuUpdate.uri");
        }
        if (name == InterfaceName.FINDMENU) {
            uri = bundle.getString("findMenu.uri");
        }
        if (name == InterfaceName.DELETEMENU) {
            uri = bundle.getString("deleteMenu.uri");
        }
        if (name == InterfaceName.ADDFILEINFO) {
            uri = bundle.getString("addFileInfo.uri");
        }



        testUrl = address + uri;
        return testUrl;
    }
    public static String getParams(){
        String params = bundle.getString("param.uri");
        return params;
    }
}