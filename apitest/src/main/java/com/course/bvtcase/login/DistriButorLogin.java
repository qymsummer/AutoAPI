package com.course.bvtcase.login;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.TokenFile;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/9 11:20
 */

public class DistriButorLogin {

    private static String account = "ybjauto001";
    private static String password = "ybjauto001";
    private static String userType = "0";

    @BeforeTest
    public void beforeTest(){
        TestConfig.ticketurl = ConfigFile.getUrl(InterfaceName.GETTICKET);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
    }
    @AfterTest
    public void afterTest(){
    }
    //获取到ticket
    public static String getTicket() throws IOException {
        HttpPost httpPost = new HttpPost(TestConfig.ticketurl);
        HttpResponse response = TestConfig.client.execute(httpPost);
        String getticket;
        getticket = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject resultjson = new JSONObject(getticket);
        JSONObject ticketlist = resultjson.getJSONObject("data");
        String ticket = (String) ticketlist.get("ticket");
        return ticket;
    }
    @Test(groups = "loginCaseDb", description = "用户登录成功")
    public void loginCaseDb() throws Exception {
        String token = getToken();
        TokenFile.witerFile(token,"E:\\Data\\Tokenfile.txt");
    }

    public static String getToken() throws IOException {
        HttpPost httpPost = new HttpPost(TestConfig.loginUrl);
        System.out.println(httpPost);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        String ticket = getTicket();
        params.add(new BasicNameValuePair("account",account));
        params.add(new BasicNameValuePair("password",password));
        params.add(new BasicNameValuePair("userType",userType));
        params.add(new BasicNameValuePair("ticket",ticket));
        httpPost.setHeader("content-type", "application/x-www-form-urlencoded");
        HttpEntity entity = new UrlEncodedFormEntity(params, "utf-8");
        httpPost.setEntity(entity);

        String result;
        //设置Cookies信
        TestConfig.client.setCookieStore(TestConfig.store);
        //执行post方法
        HttpResponse response = TestConfig.client.execute (httpPost);
        //获取响应结果
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        JSONObject resultJson = new JSONObject (result);
        String success = (String) resultJson.get("msg");
        //判断
        Assert.assertEquals("成功",success);
        JSONObject ticketlist = resultJson.getJSONObject("data");
        String token = (String) ticketlist.get("jwtToken");
        return token;
    }
}
