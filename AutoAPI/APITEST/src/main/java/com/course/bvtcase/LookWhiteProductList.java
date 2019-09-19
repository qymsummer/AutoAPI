package com.course.bvtcase;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.TokenFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LookWhiteProductList {
    @BeforeTest(
        description = "查看白名单产品列表"
    )
    public void beforeTest() {

    TestConfig.lookWhiteProductList = ConfigFile.getUrl(InterfaceName.LOOKWHITEPRODUCTLIST);

    }
    @AfterTest
    public void afterTest(){
    }
    @Test(dependsOnGroups = "loginCase",description = "查看白名单产品列表测试")
    public void lookWhiteProductList() throws Exception {
        HttpGet httpGet = new HttpGet(TestConfig.lookWhiteProductList);
        String name="jwtToken";
        String value = TokenFile.readFile();
        String result;
        httpGet.setHeader(name,value);
        HttpResponse response =  TestConfig.client.execute (httpGet);
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println("测试结果"+result);
        JSONObject JSON = new JSONObject(result);
        int success = (int) JSON.get("code");
        //判断
        Assert.assertEquals(0,success);
        System.out.println(result);
    }
}
