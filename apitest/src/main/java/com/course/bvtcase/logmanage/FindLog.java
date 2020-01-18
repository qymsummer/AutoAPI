package com.course.bvtcase.logmanage;

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
 * @Description ApiAutoTest
 * @Date 2020/1/17 15:59
 * @Author qym
 */
public class FindLog {
    @BeforeMethod(
            description = "日志查询"
    )

    public void beforeMethod() {
        TestConfig.findLog = ConfigFile.getUrl(InterfaceName.FINDLOG);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "FindLog",description = "日志查询")
    public void findLog() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.findLog);
        System.out.println(builder);
        builder.addParameter("pageNo","1");
        builder.addParameter("pageSize","10");
        builder.addParameter("operator","");
        builder.addParameter("operatorName","");
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newValue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newValue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        System.out.println(response);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println(result);
        JSONObject resultJson = new JSONObject(result);
        String  success = (String) resultJson.get("msg");
        Assert.assertEquals("成功",success);
    }
}
