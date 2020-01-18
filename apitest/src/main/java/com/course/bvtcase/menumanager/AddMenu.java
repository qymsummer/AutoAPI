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
 * Create by qym on 2020/1/13 10:30
 * @author qym
 */
public class AddMenu {
    @BeforeMethod(
            description = "新增菜单"
    )
    public void beforeMethod() {
        TestConfig.addMenuUpdate = ConfigFile.getUrl(InterfaceName.ADDMENUUPDATE);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "AddMenu",description = "新增菜单")
    public void addMenu() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.addMenuUpdate);
        System.out.println(builder);
        String menuname = "新增"+GetRandom.getRandomChar(3);
        System.out.println(menuname);
        builder.addParameter("platformId","0");
        builder.addParameter("parentId","1");
        builder.addParameter("menuName",menuname);
        builder.addParameter("url","/uri/apitest");
        builder.addParameter("orderNum","99");
        builder.addParameter("visible","0");
        builder.addParameter("path","sys");
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newValue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newValue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println(result);
        JSONObject resultJson = new JSONObject(result);
        String  success = (String) resultJson.get("msg");
        Assert.assertEquals("成功",success);
        TokenFile.witerFile(menuname,"E:\\Data\\menuname.txt");

    }
}
