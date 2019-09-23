package com.course.bvtcase.whitelistmanagement;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.DataIdFile;
import com.course.utils.TokenFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LookWhiteEnterPriseList {
    @BeforeTest(
            description = "查看白名单企业列表"
    )
    public void beforeTest() {

        TestConfig.lookWhiteEnterPriseList = ConfigFile.getUrl(InterfaceName.LOOKWHITEENTERPRISELIST);

    }
    @AfterTest
    public void afterTest(){
    }
    @Test(groups = "lookWhiteEnterPriseList",dependsOnGroups = "loginCase",description = "查看白名单企业列表测试")
    public void lookWhiteEnterPriseList() throws Exception {
        URIBuilder builder = new URIBuilder(TestConfig.lookWhiteEnterPriseList);
        String dataId = DataIdFile.readFile();
        builder.addParameter("pageNo","1");
        builder.addParameter("pageSize","10");
        builder.addParameter("productId",dataId);
        HttpGet httpGet = new HttpGet(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile();
        httpGet.setHeader(name,value);
        HttpResponse response =  TestConfig.client.execute (httpGet);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println(dataId);
        System.out.println("测试结果:"+"\t"+result);
    }
}
