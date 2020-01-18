package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/9 11:20
 * @author qym
 */

public class ConfigFile {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);


    public static String getUrl(InterfaceName name) {
        String address = bundle.getString("test.uri");
        String uri = "";
        String testUrl;

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
        if (name == InterfaceName.DELETEORG) {
            uri = bundle.getString("deleteOrg.uri");
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
        if (name == InterfaceName.SELECTORGANIZATIONTREE) {
            uri = bundle.getString("selectOrganizationTree.uri");
        }
        if (name == InterfaceName.FINDLOG) {
            uri = bundle.getString("findLog.uri");
        }
        if (name == InterfaceName.DELETEROLE) {
            uri = bundle.getString("deleteRole.uri");
        }
        if (name == InterfaceName.DELETEGRAYORG) {
            uri = bundle.getString("deleteGrayOrg.uri");
        }
        if (name == InterfaceName.DELETEORGPRODUCTAUTH) {
            uri = bundle.getString("deletePlatform.uri");
        }
        testUrl = address + uri;
        return testUrl;
    }
    public static String getParams(){
        String params = bundle.getString("param.uri");
        return params;
    }
}