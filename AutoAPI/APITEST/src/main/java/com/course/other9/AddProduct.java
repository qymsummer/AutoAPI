package com.course.other9;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddProduct {
    private CookieStore store;
    @BeforeTest
    public void beforeTest(){
        TestConfig.addProductUrl = ConfigFile.getUrl(InterfaceName.ADDPRODUCT);
    }
    @AfterTest
    public void afterTest(){
    }
    @Test(dependsOnGroups = "loginCase",description = "添加用户接口测试")
    public void addProduct() throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(TestConfig.addProductUrl);
        JSONObject params = new JSONObject();
        params.put("repaymentMeansIds","9");
        params.put("loanUseIds","1");
        params.put("guaranteeMeansIds","5");
        params.put("name","产名是");
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
        params.put("fullProvince","true");
        request.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(params.toString(),"utf-8");
        request.setEntity(entity);
        String result;
        //设置Cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response =  client.execute (request);
        //获取响应结果
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println(result);
    }
}
