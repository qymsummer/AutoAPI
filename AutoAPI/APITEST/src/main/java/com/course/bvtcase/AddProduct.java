package com.course.bvtcase;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class AddProduct {
    @BeforeTest(
            description = "获取到添加产品的url"
    )
    public void beforeTest() {

        TestConfig.addProductUrl = ConfigFile.getUrl(InterfaceName.ADDPRODUCT);
    }
    @AfterTest
    public void afterTest(){
    }
    @Test(dependsOnGroups = "loginCase",description = "添加用户接口测试")
    public void addProduct() throws IOException {
        HttpPost httpPost = new HttpPost(TestConfig.addProductUrl);
        JSONObject params = new JSONObject();
        params.put("repaymentMeansIds","9");
        params.put("loanUseIds","1,4");
        params.put("guaranteeMeansIds","5");
        params.put("fullProvince","true");
        params.put("quotaRangeLimitEnum","false");
        params.put("name","测试01");
        params.put("termRangeStart","1");
        params.put("termRangeEnd","3");
        params.put("annualInterestRangeStart","4");
        params.put("annualInterestRangeEnd","5");
        params.put("quotaRangeEnd","100");
        params.put("acceptanceTimeStart","6");
        params.put("acceptanceTimeEnd","7");
        params.put("introduce","产品介绍");
        params.put("characteristic","产品特色");
        params.put("applyRequirement","申请条件");
        params.put("requirementMaterial","所需材料");
        params.put("labelIds","24");
        httpPost.setHeader("content-type", "application/json");
        String name="jwtToken";
        String value="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiYWVhMGU4Ny1iNDE2LTRkNmEtOWJiYy0yZDQwNzY2NWJmOTIiLCJzdWIiOiIzMjYiLCJpc3MiOiJKcnlfYWRtaW4iLCJpYXQiOjE1NjgyNzI5ODYsImV4cCI6MTU2ODg3Nzc4Nn0.4T2VUdwxBDlN4zs5I0mfizrXow1hB-0AZQu_91bKNhw";
        httpPost.setHeader(name,value);
        StringEntity entity = new StringEntity(params.toString(),"utf-8");
        httpPost.setEntity(entity);
        String result;
        TestConfig.store = TestConfig.client.getCookieStore();
        List<Cookie> cookieList = TestConfig.store.getCookies();
        /*
        for(Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("name = "+name+",value = "+value);
        }

         */
        //设置Cookies信息
        TestConfig.client.setCookieStore(TestConfig.store);
        //执行post方法
        HttpResponse response =  TestConfig.client.execute (httpPost);
        //获取响应结果
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println(result);
        /*
        JSONObject JSON = new JSONObject(result);
        int success = (int) JSON.get("code");
        //判断
        Assert.assertEquals(0,success);
        System.out.println(result);
         */


    }
}
