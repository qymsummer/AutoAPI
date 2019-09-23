package com.course.bvtcase.whitelistmanagement;

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

public class LookWhiteList {
    @BeforeTest(
            description = "查看白名单列表"
    )
    public void beforeTest() {
        TestConfig.lookWhiteList = ConfigFile.getUrl(InterfaceName.LOOKWHITELIST);
    }
    @AfterTest
    public void afterTest(){

    }
    @Test(groups = "lookWhiteList",dependsOnGroups = "loginCase",description = "查看白名单列表测试")
    public void lookWhiteList() throws Exception {
        HttpGet httpGet = new HttpGet(TestConfig.lookWhiteList);
        String name="jwtToken";
        String value = TokenFile.readFile();
        String result;
        httpGet.setHeader(name,value);
        HttpResponse response =  TestConfig.client.execute (httpGet);
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println("测试结果:"+"\t"+result);
        JSONObject JSON = new JSONObject(result);
        int success = (int) JSON.get("code");
        //判断
        Assert.assertEquals(0,success);
    }
}
