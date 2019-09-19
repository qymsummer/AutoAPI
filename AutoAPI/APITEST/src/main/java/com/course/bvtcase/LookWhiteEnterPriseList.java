package com.course.bvtcase;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.DataIdFile;
import com.course.utils.TokenFile;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

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
    @Test(dependsOnGroups = "loginCase",description = "查看白名单企业列表测试")
    public void lookWhiteProductList() throws Exception {

        HttpGet httpGet = new HttpGet(TestConfig.lookWhiteEnterPriseList);
        String name="jwtToken";
        String value = TokenFile.readFile();
        httpGet.setHeader(name,value);
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        String dataId = DataIdFile.readFile();
        list.add(new BasicNameValuePair("pageNo","1"));
        list.add(new BasicNameValuePair("pageSize","10"));
        list.add(new BasicNameValuePair("productId",dataId));
        httpGet.setHeader("content-type", "application/x-www-form-urlencoded");

        TestConfig.store = TestConfig.client.getCookieStore();
        List<Cookie> cookieList = TestConfig.store.getCookies();
        HttpResponse response = TestConfig.client.execute (httpGet);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println(result);

    }
}
