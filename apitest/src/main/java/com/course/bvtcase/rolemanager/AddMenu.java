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
 * Create by qym on 2020/1/9 17:42
 * @author qym
 */
public class AddMenu {
    @BeforeMethod(
            description = "角色添加菜单"
    )

    public void beforeMethod() {
        TestConfig.addRoleMenu = ConfigFile.getUrl(InterfaceName.ADDROLEMENU);
    }
    @AfterMethod
    public void afterMethod(){

    }


    @Test(groups = "AddMenu",description = "角色添加菜单")
    public void addMenu() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.addRoleMenu);
        System.out.println(builder);
        String roleId = TokenFile.readFile("E:\\Data\\RoleId.txt");
        builder.addParameter("roleId",roleId);
        builder.addParameter("menuId","[{\"menuId\":122},{\"menuId\":123},{\"menuId\":124}]");
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newValue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newValue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println(result);
        JSONObject jsonObject = new JSONObject(result);
        String  success = (String) jsonObject.get("msg");
        Assert.assertEquals("成功",success);
    }
}
