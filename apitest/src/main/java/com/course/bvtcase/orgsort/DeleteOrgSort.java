package com.course.bvtcase.orgsort;

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
 * @Date 2020/1/18 10:25
 * @Author qym
 */
public class DeleteOrgSort {
    @BeforeMethod(
            description = "机构分类删除"
    )

    public void beforeMethod() {

        TestConfig.deleteById = ConfigFile.getUrl(InterfaceName.DELETEBYID);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "DeleteOrgSort",description = "机构分类删除")
    public void deleteOrgSort() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.deleteById);
        System.out.println(builder);
        String id = TokenFile.readFile("E:\\Data\\ID.txt");
        String newId = id.replaceAll("[\\t\\n\\r\\s]","");
        builder.addParameter("id",newId);
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
