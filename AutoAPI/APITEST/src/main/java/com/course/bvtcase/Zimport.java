package com.course.bvtcase;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.DataIdFile;
import com.course.utils.TokenFile;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class Zimport {
    @BeforeTest(
            description = "获取到添加产品的url"
    )
    public void beforeTest() {

        TestConfig.importwhitelistUrl = ConfigFile.getUrl(InterfaceName.IMPORTWHITELIST);
    }
    @AfterTest
    public void afterTest(){

    }
    @Test
    public void importWhiteListUrl() throws Exception {
        HttpPost httpPost = new HttpPost(TestConfig.importwhitelistUrl);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        File file = new File("C:\\Data\\白名单模板.xlsx");
        multipartEntityBuilder.addBinaryBody("file",file);
        //multipartEntityBuilder.addTextBody("file","白名单模板.xlsx", ContentType.parse("multipart/form-data"));
        String name="jwtToken";
        String value = TokenFile.readFile();
        System.out.println("==========");
        System.out.println(value);
        String dataId = DataIdFile.readFile();
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("id","1175268708512174081");

        System.out.println(dataId);
        System.out.println("==========");
        JSONObject params = new JSONObject(map);
        httpPost.setHeader(name,value);
        StringEntity entity = new StringEntity(params.toString(),"utf-8");
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);
        httpPost.setEntity(entity);



        HttpResponse response =  TestConfig.client.execute (httpPost);
        Thread.sleep(3000);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println("测试结果:"+"\t"+result);
    }
}