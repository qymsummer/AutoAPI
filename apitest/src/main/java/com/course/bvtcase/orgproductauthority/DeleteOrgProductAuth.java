package com.course.bvtcase.orgproductauthority;

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
 * @Date 2020/1/18 9:49
 * @Author qym
 */
public class DeleteOrgProductAuth {
    @BeforeMethod(
            description = "机构产品权限删除"
    )

    public void beforeMethod() {
        TestConfig.deleteOrgProductAuth = ConfigFile.getUrl(InterfaceName.DELETEORGPRODUCTAUTH);
    }
    @AfterMethod
    public void afterMethod(){

    }
    @Test(groups = "DeleteOrgProductAuth",description = "机构产品权限删除")
    public void deleteOrgProductAuth() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.deleteOrgProductAuth);
        System.out.println(builder);
        String orgId = TokenFile.readFile("E:\\Data\\OrgID.txt");
        String newOrgId = orgId.replaceAll("[\\t\\n\\r\\s]","");
        builder.addParameter("orgId",newOrgId);
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
