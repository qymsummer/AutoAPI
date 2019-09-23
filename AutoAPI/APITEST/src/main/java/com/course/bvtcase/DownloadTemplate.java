package com.course.bvtcase;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.DataIdFile;
import com.course.utils.TokenFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DownloadTemplate {
    @BeforeTest(
            description = "下载模版"
    )
    public void beforeTest() {

        TestConfig.downloadTemplateUrl = ConfigFile.getUrl(InterfaceName.DOWNLOADTEMPLATE);

    }
    @AfterTest
    public void afterTest(){
    }
    @Test(groups = "downloadTemplate",dependsOnGroups = "loginCase",description = "查看白名单企业列表测试")
    public void downloadTemplate() throws Exception {
        URIBuilder builder = new URIBuilder(TestConfig.downloadTemplateUrl);
        //String dataId = DataIdFile.readFile();
        builder.addParameter("templateType","WHITE_LIST");
        //builder.addParameter("pageSize","10");
        HttpGet httpGet = new HttpGet(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile();
        httpGet.setHeader(name,value);
        HttpResponse response =  TestConfig.client.execute (httpGet);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println("测试结果:"+"\t"+result);
        boolean status = result.contains("docProps/app.xmlPK");
        int flag = 1;
        if(status){
            flag = 0;
        }
        //判断
        Assert.assertEquals(0,flag);
    }
}
