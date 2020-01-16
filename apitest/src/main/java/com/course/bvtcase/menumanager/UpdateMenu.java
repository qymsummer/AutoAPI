package com.course.bvtcase.menumanager;

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
 * Create by qym on 2020/1/13 11:21
 */
public class UpdateMenu {
    @BeforeMethod(
            description = "修改菜单"
    )
    public void beforeMethod() {
        TestConfig.addMenuUpdate = ConfigFile.getUrl(InterfaceName.ADDMENUUPDATE);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "UpdateMenu",description = "修改菜单")
    public void updateMenu() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.addMenuUpdate);
        String menuname = "修改"+ GetRandom.getRandomChar(3);
        String menuId = TokenFile.readFile("E:\\Data\\menuId.txt");
        System.out.println(menuname);
        builder.addParameter("platformId","0");
        builder.addParameter("parentId","1");
        builder.addParameter("menuName",menuname);
        builder.addParameter("url","/uri/apitest");
        builder.addParameter("path","update");
        builder.addParameter("orderNum","88");
        builder.addParameter("visible","0");
        builder.addParameter("menuId",menuId);
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newvalue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newvalue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println(result);
        JSONObject resultJson = new JSONObject(result);
        String  success = (String) resultJson.get("msg");
        Assert.assertEquals("成功",success);
    }
}
