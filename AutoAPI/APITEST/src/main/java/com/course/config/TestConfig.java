package com.course.config;


import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestConfig {
    //登录URL
    public static String loginUrl;
    public static String addProductUrl;
    public static CookieStore store;
    public static DefaultHttpClient client = new DefaultHttpClient();
}
