package com.course.other;

import com.course.config.TestConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    //private static final Object HTTP = ;
    private String url;
    private ResourceBundle bundle;
    //用来存储cookies信息的变量
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle ("application", Locale.CHINA);
        url = bundle.getString ("test.url");
    }
    @Test
    public void testPostCookies() throws IOException {
        String url = bundle.getString ("login.uri");
        String testUrl = this.url+url;
        DefaultHttpClient client = new DefaultHttpClient();
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("account","TLCB114567"));
        params.add(new BasicNameValuePair("password","TLCB114567"));
        params.add(new BasicNameValuePair("userType","1"));
        HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
        HttpPost request = new HttpPost(testUrl);
        request.setEntity(entity);
        String result;
        //设置Cookies信
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response = client.execute (request);
        //获取响应结果
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println (result);

    }
}
