package com.course.bvtcase.productmanagement;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.DataIdFile;
import com.course.utils.TokenFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.cookie.Cookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;


public class ProductShelvesUrl {

    @BeforeTest(description = "获取到产品上下架的url")
    public void beforeTest(){

        TestConfig.productShelvesUrl = ConfigFile.getUrl(InterfaceName.PRODUCTSHELVES);
    }
    @AfterTest
    public void afterTest(){
    }
    public static String getUrl() throws Exception {
        String url = TestConfig.productShelvesUrl;
        String dataId = DataIdFile.readFile();
        String param = ConfigFile.getParams();
        System.out.println(dataId);
        String testUrl = url+dataId+param;
        testUrl =testUrl.replaceAll("\n", "");
        System.out.println(testUrl);
        return testUrl;
    }
    @Test(groups = "productShelvesUrl",dependsOnGroups="LoanApproverloginCase",description = "产品上下架测试")
    public void productShelvesUrl() throws Exception {
        HttpPut httpPut = new HttpPut(getUrl());
        String name="jwtToken";
        String value = TokenFile.readFile();
        httpPut.setHeader(name,value);
        String result;
        //设置Cookies信
        TestConfig.client.setCookieStore(TestConfig.store);
        TestConfig.store = TestConfig.client.getCookieStore();
        List<Cookie> cookieList = TestConfig.store.getCookies();
        //执行post方法
        HttpResponse response = TestConfig.client.execute (httpPut);
        //获取响应结果
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        JSONObject resultJson = new JSONObject (result);
        int success = (int) resultJson.get("code");
        //判断
        Assert.assertEquals(0,success);
        System.out.println(result);
    }
}
