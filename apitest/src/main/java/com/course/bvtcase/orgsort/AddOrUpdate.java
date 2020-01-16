package com.course.bvtcase.orgsort;

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
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/9 11:20
 */

public class AddOrUpdate {
    @BeforeMethod(
            description = "机构分类添加"
    )

    public void beforeMethod() {
        TestConfig.addOrUpdate = ConfigFile.getUrl(InterfaceName.ADDORUPDATE);
    }
    @AfterMethod
    public void afterMethod(){

    }
    @Test(groups = "addPrUpdate",description = "添加机构分类")
    public void runAddUpate() throws Exception, URISyntaxException {
        String result = addUpdate();
        TokenFile.witerFile(result,"E:\\Data\\orgTypeName.txt");
    }
    public static String addUpdate() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.addOrUpdate);
        System.out.println(builder);
        String random= GetRandom.getRandomChar(5);
        String productName= "添加"+random;
        System.out.println(productName);
        builder.addParameter("id","");
        builder.addParameter("parentId","35");
        builder.addParameter("orgTypeName",productName);
        builder.addParameter("orgTypeNo","77");
        builder.addParameter("remark",productName);
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newvalue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newvalue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        //System.out.println(result);
        JSONObject resultJson = new JSONObject(result);
        String  success = (String) resultJson.get("msg");
        Assert.assertEquals("成功",success);
        System.out.println(result);
        return productName;
    }
}
