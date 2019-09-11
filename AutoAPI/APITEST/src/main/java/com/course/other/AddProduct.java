package com.course.other;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddProduct {
    private CookieStore store;
    @BeforeTest
    public void beforeTest(){
        TestConfig.addProductUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
    }
    @AfterTest
    public void afterTest(){
    }
    @Test(description = "添加产品")
    public void addProduct() throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(TestConfig.addProductUrl);
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("repaymentMeansIds","9"));
        params.add(new BasicNameValuePair("loanUseIds","1"));
        params.add(new BasicNameValuePair("guaranteeMeansIds","5"));
        params.add(new BasicNameValuePair("name","产名"));
        params.add(new BasicNameValuePair("termRangeStart","1"));
        params.add(new BasicNameValuePair("termRangeEnd","3"));
        params.add(new BasicNameValuePair("annualInterestRangeStart","4"));
        params.add(new BasicNameValuePair("annualInterestRangeEnd","5"));
        params.add(new BasicNameValuePair("quotaRangeEnd","100"));
        params.add(new BasicNameValuePair("acceptanceTimeStart","6"));
        params.add(new BasicNameValuePair("acceptanceTimeEnd","7"));
        params.add(new BasicNameValuePair("introduce","产品介绍"));
        params.add(new BasicNameValuePair("characteristic","产品特色"));
        params.add(new BasicNameValuePair("applyRequirement","申请条件"));
        params.add(new BasicNameValuePair("requirementMaterial","所需材料"));
        params.add(new BasicNameValuePair("labelIds","24"));
        //params.add(new BasicNameValuePair("fullProvince",fullProvince));
        request.setHeader("content-type", "application/json");
        HttpEntity entity = new UrlEncodedFormEntity(params,"UTF-8");
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
