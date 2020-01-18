package com.course.bvtcase.organizationtree;

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
 * @Date 2020/1/17 15:52
 * @Author qym
 */
public class SelectOrganizationTree {
    @BeforeMethod(
            description = "机构树查询"
    )

    public void beforeMethod() {
        TestConfig.selectOrganizationTree = ConfigFile.getUrl(InterfaceName.SELECTORGANIZATIONTREE);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "selectOrganizationTree",description = "机构树查询")
    public void selectOrganizationTree() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.selectOrganizationTree);
        System.out.println(builder);
        String organizationName = TokenFile.readFile("E:\\Data\\organizationName.txt");
        String newOrganizationName = organizationName.replaceAll("[\\t\\n\\r\\s]","");
        builder.addParameter("organizationName",newOrganizationName);
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
