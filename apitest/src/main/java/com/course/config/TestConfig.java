package com.course.config;


import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/9 11:20
 * @author qym
 */

public class TestConfig {

    /**
     *  获取TICKET URL
     */
    public static String ticketurl;
    /**
     *  登录URL
     */
    public static String loginUrl;
    /**
     *  添加机构分类URL
     */
    public static String addOrUpdate;
    /**
     *  查找机构分类URL
     */
    public static String findOrg;
    /**
     *  新增机构
     */
    public static String addOrgName;
    /**
     *  获取TICKET URL
     */
    /**
     * 机构查询
     */
    public static String findOrgName;
    /**
     *  机构删除
     */
    public static String deleteOrg;
    /**
     *  新增机构产品权限
     */
    public static String addOrgProductAuth;
    /**
     *  查询机构产品权限
     */
    public static String findOrgProductAuth;
    /**
     *  灰度机构添加
     */
    public static String addGraySetting;
    /**
     *  灰度机构查询
     */
    public static String findGrayOrg;
    /**
     *  新增角色
     */
    public static String addRoleOrUpdate;
    /**
     *  角色查询
     */
    public static String findRoleList;
    /**
     *  角色菜单添加
     */
    public static String addRoleMenu;
    /**
     *  新增用户
     */
    public static String addUser;
    /**
     *  查询用户
     */
    public static String findUser;
    /**
     *  重置密码
     */
    public static String initPassword;
    /**
     *  新增菜单
     */
    public static String addMenuUpdate;
    /**
     *  菜单查询
     */
    public static String findMenu;
    /**
     *  菜单删除
     */
    public static String deleteMenu;
    /**
     *  文件上传
     */
    public static String addFileInfo;
    /**
     * 机构树查询
     */
    public static String selectOrganizationTree;
    /**
     * 日志查询
     */
    public static String findLog;
    /**
     * 角色删除
     */
    public static String deleteRole;
    /**
     * 灰度机构删除
     */
    public static String deleteGrayOrg;
    /**
     * 产品权限删除
     */
    public static String deleteOrgProductAuth;
    /**
     * 机构分类删除
     */
    public static String deleteById;
    public static CookieStore store;
    public static DefaultHttpClient client = new DefaultHttpClient();
}
