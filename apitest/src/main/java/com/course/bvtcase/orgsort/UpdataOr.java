package com.course.bvtcase.orgsort;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.*;
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

public class UpdataOr {
    @BeforeMethod(
            description = "机构分类修改"
    )

    public void beforeMethod() {

        TestConfig.addOrUpdate = ConfigFile.getUrl(InterfaceName.ADDORUPDATE);
    }
    @AfterMethod
    public void afterMethod(){

    }
    @Test(groups = "UpdateOr",description = "机构分类修改")

    public void  UpdateOr() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.addOrUpdate);
        String random= GetRandom.getRandomChar(5);
        String productName= "修改"+random;
        System.out.println(productName);
        String ID = TokenFile.readFile("E:\\Data\\ID.txt");
        builder.addParameter("id",ID);
        builder.addParameter("parentId","25");
        builder.addParameter("orgTypeName",productName);
        builder.addParameter("orgTypeNo","96");
        builder.addParameter("remark",productName);
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newvalue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newvalue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        JSONObject resultJson = new JSONObject(result);
        String  success = (String) resultJson.get("msg");
        Assert.assertEquals("成功",success);
        System.out.println(result);
    }
}
