package com.course.bvtcase.grayscaleorg;

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
 * Create by qym on 2020/1/10 11:37
 */
public class AddGrayScaleOrg {
    @BeforeMethod(
            description = "新增灰度机构"
    )

    public void beforeMethod() {
        TestConfig.addGraySetting = ConfigFile.getUrl(InterfaceName.ADDGRAYSETTING);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "AddGrayScaleOrg",description = "新增灰度机构")
    public void addGrayScaleOrg() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.addGraySetting);
        System.out.println(builder);
        String orgId = TokenFile.readFile("E:\\Data\\FictitiousorgId.txt");
        builder.addParameter("orgId",orgId);
        builder.addParameter("platformId","0");
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newvalue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newvalue);
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
