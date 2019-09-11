package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle;
    //用来存储cookies信息的变量
    private CookieStore store;
    /*
    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle ("application", Locale.CHINA);
        url = bundle.getString ("test.url");
    }
    @Test
    public void testGetCookies() throws IOException {
        String result;
        //从配置文件中 拼接测试的url
        String uri = bundle.getString ("getCookies.url");
        String testUrl = this.url+uri;
        //测试逻辑代码书写
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString ( response.getEntity(),"utf-8");
        System.out.println (result);
        //获取Cookies信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for(Cookie cookie: cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
        }
    }

     */
    @Test
    public void testPostCookies() throws IOException {
        String url = bundle.getString ("getWithPostCookies.url");
        //拼写测试地址
        String testUrl = this.url+url;
        //声明一个client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(testUrl);
        //添加参数
        JSONObject param = new JSONObject ();
        param.put("userName","TLCB114567");
        param.put("password","TLCB114567");
        param.put("userType","1");

        //设置请求头信息 设置header
        post.setHeader ("content-type","application/json");
        //将参数信息添加到header
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity (entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //设置Cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response = client.execute (post);
        //获取响应结果
        result = EntityUtils.toString ( response.getEntity(),"utf-8");
        System.out.println (result);

        //处理结果，就是判断返回结果是否符合预期
        //将返回的响应结果字符串转换成json对象
        JSONObject resultJson = new JSONObject (result);
        //获取到结果值
        String success = (String) resultJson.get("huhansan");
        String status = (String) resultJson.get("status");
        //具体的判断返回结果的值
        Assert.assertEquals("success",success);
        Assert.assertEquals ("1",status );
    }
}