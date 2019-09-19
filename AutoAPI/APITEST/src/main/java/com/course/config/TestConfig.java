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

    public static CookieStore store;
    public static DefaultHttpClient client = new DefaultHttpClient();
}
