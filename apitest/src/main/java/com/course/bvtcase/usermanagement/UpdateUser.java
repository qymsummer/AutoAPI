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
 * Create by qym on 2020/1/10 15:06
 * @author qym
 */
public class UpdateUser {
    @BeforeMethod(
            description = "用户修改"
    )

    public void beforeMethod() {
        TestConfig.addUser = ConfigFile.getUrl(InterfaceName.ADDUSER);
    }
    @AfterMethod
    public void afterMethod(){

    }

    @Test(groups = "UpdateUser",description = "用户修改")
    public void updateUser() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.addUser);
        String orgId = TokenFile.readFile("E:\\Data\\OrgID.txt");
        System.out.println(orgId);
        String userId = TokenFile.readFile("E:\\Data\\userId.txt");
        String idCard= GetNum.getRandomChar(18);
        Thread.sleep(100);
        String account= TokenFile.readFile("E:\\Data\\accountid.txt");
        System.out.println(account);
        String userName = GetName.getRandomChar(2);
        builder.addParameter("macAddress","33-33-33-33-33-33");
        builder.addParameter("userId",userId);
        builder.addParameter("idCard",idCard);
        builder.addParameter("userName",userName);
        builder.addParameter("account",account);
        builder.addParameter("orgId",orgId);
        builder.addParameter("status","0");
        builder.addParameter("roleIds","33");
        builder.addParameter("remark","一级管理员");
        builder.addParameter("phonenumber","13572562600");
        builder.addParameter("roleNames","一级机构管理员");
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newValue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newValue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        JSONObject jsonObject = new JSONObject(result);
        String  success = (String) jsonObject.get("msg");
        Assert.assertEquals("成功",success);
        System.out.println(result);
        TokenFile.witerFile(account,"E:\\Data\\account.txt");
    }
}
