package com.course.bvtcase.usermanagement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.TokenFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/10 14:58
 * @author qym
 */
public class FindUser {
    @BeforeMethod(
            description = "用户查询"
    )

    public void beforeMethod() {
        TestConfig.findUser = ConfigFile.getUrl(InterfaceName.FINDUSER);
    }
    @AfterMethod
    public void afterMethod(){

    }

    @Test(groups = "FindUser",description = "用户查询")
    public void findUser() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.findUser);
        String account = TokenFile.readFile("E:\\Data\\account.txt");
        String newAccount = account.replaceAll("[\\t\\n\\r\\s]","");
        builder.addParameter("pageNo","1");
        builder.addParameter("pageSize","10");
        builder.addParameter("account",newAccount);
        builder.addParameter("orgName","");
        builder.addParameter("userName","");
        builder.addParameter("roleNames","");
        builder.addParameter("userType","");
        builder.addParameter("status","");
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newValue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newValue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        JSONObject resultJson = new JSONObject(result);
        String  success = (String) resultJson.get("msg");
        Assert.assertEquals("成功",success);
        com.alibaba.fastjson.JSONObject jsonpObject = JSON.parseObject(result);
        JSONArray arrs = jsonpObject.getJSONObject("data").getJSONArray("records");
        System.out.println(result);
        int code = (int) resultJson.get("code");
        int a =1;
        if (code!=a) {
            for (int i = 0; i < arrs.size(); i++) {
                com.alibaba.fastjson.JSONObject obj = arrs.getJSONObject(i);
                String id = obj.getString("userId");
                String accountid = obj.getString("account");
                String newAccountId = account.replaceAll("[\\t\\n\\r\\s]","");
                TokenFile.witerFile(id,"E:\\Data\\userId.txt");
                System.out.println(id);
                TokenFile.witerFile(newAccountId,"E:\\Data\\accountid.txt");
                System.out.println(newAccountId);
            }
        }else {
        }
    }
}
