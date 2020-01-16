package com.course.bvtcase.orgproductauthority;

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
 * Create by qym on 2020/1/9 11:34
 */
public class FindOrgProductAuth {
    @BeforeMethod(
            description = "机构产品权限查询"
    )

    public void beforeMethod() {
        TestConfig.findOrgProductAuth = ConfigFile.getUrl(InterfaceName.FINDORGPRODUCTAUTH);
    }

    @AfterMethod
    public void afterMethod() {

    }

    @Test(groups = "findOrgProductAuth", description = "机构产品权限查询")
    public void findOrgProductAuth() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.findOrgProductAuth);
        String organizationName = TokenFile.readFile("E:\\Data\\organizationName.txt");
        //String newOrgTyeName = organizationName.replaceAll("[\\t\\n\\r\\s]","");
        builder.addParameter("pageNo","1");
        builder.addParameter("pageSize","10");
        builder.addParameter("orgSimpleName","");
        builder.addParameter("organizationName",organizationName);
        builder.addParameter("platformIds","");
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


