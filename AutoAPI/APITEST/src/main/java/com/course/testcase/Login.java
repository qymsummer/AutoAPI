package com.course.testcase;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.mongodb.client.internal.OperationExecutor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Login {

    @BeforeTest
    public void beforeTest(){
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
    }
    @Test(groups = "loginCase", description = "用户登录成功")
    public void loginCase() throws IOException {
        HttpPost request = new HttpPost(TestConfig.loginUrl);
        List<NameValuePair> params=new ArrayList<NameValuePair>();

        String account = "TLCB114567";
        String password = "TLCB114567";
        String userType = "1";
        params.add(new BasicNameValuePair("account",account));
        params.add(new BasicNameValuePair("password",password));
        params.add(new BasicNameValuePair("userType",userType));
        request.setHeader("content-type", "application/x-www-form-urlencoded");
        HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
        request.setEntity(entity);
        String result;
        //设置Cookies信
        TestConfig.client.setCookieStore(TestConfig.store);
        //执行post方法
        HttpResponse response = TestConfig.client.execute (request);
        //获取响应结果
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        org.json.JSONObject resultJson = new JSONObject(result);
        String success = (String) resultJson.get("data");
        //判断
        Assert.assertEquals("登录成功",success);
        System.out.println(result);
    }
}
