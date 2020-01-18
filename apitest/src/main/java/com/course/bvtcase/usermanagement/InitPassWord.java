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
 * Create by qym on 2020/1/10 15:20
 * @author qym
 */
public class InitPassWord {
    @BeforeMethod(
            description = "重置账户"
    )

    public void beforeMethod()
    {
        TestConfig.initPassword = ConfigFile.getUrl(InterfaceName.INITPASSWORD);
    }
    @AfterMethod
    public void afterMethod(){
    }

    @Test(groups = "InitPassWord",description = "重置账户")
    public void initPassWord() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.initPassword);
        System.out.println(builder);
        String userId = TokenFile.readFile("E:\\Data\\userId.txt");
        String newUserId = userId.replaceAll("[\\t\\n\\r\\s]","");
        builder.addParameter("userId", newUserId);
        HttpPost httpPost = new HttpPost(builder.build());
        String name = "jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newValue = value.replaceAll("[\\t\\n\\r\\s]", "");
        httpPost.setHeader(name, newValue);
        HttpResponse response = TestConfig.client.execute(httpPost);
        String result;
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = new JSONObject(result);
        String success = (String) jsonObject.get("msg");
        Assert.assertEquals("成功", success);
        System.out.println(result);
    }
}
