package com.course.bvtcase.loginh5;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.ConfigFileH5;
import com.course.utils.TokenFile;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class H5Login {
    @BeforeTest
    public void beforeTest() {
        TestConfig.h5Loign = ConfigFileH5.getUrl(InterfaceName.H5LOGIN);
    }

    @AfterTest
    public void afterTest() {

    }

    @Test
    public void getToken() throws URISyntaxException, IOException {
        HttpGet httpGet = new HttpGet(TestConfig.h5Loign);
        String name="zytoken";
        String value = "%2Fuser%2Fzytoken%b2717ea08d7db7e7ba0e8129c6f573e2f12599790e86d5c54f6476d59e";
        httpGet.setHeader(name,value);
        HttpResponse response =  TestConfig.client.execute (httpGet);

        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println("测试结果:"+"\t"+result);
        boolean status = result.contains("浙里掌上贷");
        int flag = 1;
        if(status){
            flag = 0;
        }
        System.out.println(result);

        System.out.println(flag);
        //判断
        Assert.assertEquals(0,flag);
    }
}