package com.course.bvtcase.rolemanager;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.GetRandom;
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
 * Create by qym on 2020/1/9 15:36
 */
public class AddRole {
    @BeforeMethod(
            description = "角色添加"
    )

    public void beforeMethod() {
        TestConfig.addRoleOrUpdate = ConfigFile.getUrl(InterfaceName.ADDROLEORUPDATE);
    }
    @AfterMethod
    public void afterMethod(){

    }


    @Test(groups = "AddRole",description = "角色添加")
    public void addRole() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.addRoleOrUpdate);
        System.out.println(builder);
        String random= GetRandom.getRandomChar(5);
        String roleName= "角色"+random;
        System.out.println(roleName);
        builder.addParameter("roleName",roleName);
        builder.addParameter("dataScope","2");
        builder.addParameter("orgIds","");
        builder.addParameter("remark","描述信息");
        builder.addParameter("userType","0");
        builder.addParameter("parentId","33");
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newvalue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newvalue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        //System.out.println(result);
        JSONObject resultJson = new JSONObject(result);
        String  success = (String) resultJson.get("msg");
        Assert.assertEquals("成功",success);
        TokenFile.witerFile(roleName,"E:\\Data\\RoleName.txt");
    }
}