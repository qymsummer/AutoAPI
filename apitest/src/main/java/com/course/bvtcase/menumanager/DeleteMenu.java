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
 * Create by qym on 2020/1/13 13:37
 */
public class DeleteMenu {
    @BeforeMethod(
            description = "菜单删除"
    )
    public void beforeMethod() {
        TestConfig.deleteMenu = ConfigFile.getUrl(InterfaceName.DELETEMENU);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "DeleteMenu",description = "菜单删除")
    public void deleteMenu() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.deleteMenu);
        System.out.println(builder);
        String menuId = TokenFile.readFile("E:\\Data\\menuId.txt");
        String newmenuId = menuId.replaceAll("[\\t\\n\\r\\s]","");
        builder.addParameter("menuId",newmenuId);
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
