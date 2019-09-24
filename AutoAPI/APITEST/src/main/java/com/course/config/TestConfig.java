package com.course.config;


import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestConfig {
    //获取TICKET URL
    public static String ticketurl;
    //登录URL
    public static String loginUrl;
    //添加产品URL
    public static String addProductUrl;
    //编辑产品URL
    public static String updateProductUrl;
    //查看产品
    public static String lookProductUrl;
    //查看白名单产品列表
    public static String lookWhiteProductList;
    //查看白名单列表
    public static String lookWhiteList;
    //查看白名单企业列表
    public static String lookWhiteEnterPriseList;
    //导入白名单
    public static String importwhitelistUrl;
    //模版下载
    public static String downloadTemplateUrl;
    //产品上下架
    public static String productShelvesUrl;
    //上下架所需参数
    public static String paramUrl;
    //退出登录
    public static String loginOut;

    public static CookieStore store;
    public static DefaultHttpClient client = new DefaultHttpClient();
}
