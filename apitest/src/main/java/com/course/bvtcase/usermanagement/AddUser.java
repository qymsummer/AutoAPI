package com.course.bvtcase.usermanagement;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.*;
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
 * Create by qym on 2020/1/10 14:23
 */
public class AddUser {
    @BeforeMethod(
            description = "新增用户"
    )
    public void beforeMethod() {
        TestConfig.addUser = ConfigFile.getUrl(InterfaceName.ADDUSER);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "AddUser",description = "新增用户")
    public void addUser() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.addUser);
        String orgId = TokenFile.readFile("E:\\Data\\FictitiousorgId.txt");
        System.out.println(orgId);
        String idCard= GetNum.getRandomChar(18);
        Thread.sleep(100);
        String account= GetNum.getRandomChar(7);
        System.out.println(account);
        String userName = GetName.getRandomChar(2);
        builder.addParameter("macAddress","22-22-22-22-22-22");
        builder.addParameter("userId","");
        builder.addParameter("idCard",idCard);
        builder.addParameter("userName",userName);
        builder.addParameter("account",account);
        builder.addParameter("orgId",orgId);
        builder.addParameter("status","0");
        builder.addParameter("roleIds","34");
        builder.addParameter("remark","管理员");
        builder.addParameter("phonenumber","13572562626");
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newvalue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newvalue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        JSONObject resultJson = new JSONObject(result);
        String  success = (String) resultJson.get("msg");
        Assert.assertEquals("成功",success);
        System.out.println(result);
        TokenFile.witerFile(account,"E:\\Data\\account.txt");
    }
}
