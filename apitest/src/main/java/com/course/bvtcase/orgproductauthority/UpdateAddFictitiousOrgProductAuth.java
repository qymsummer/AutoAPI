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
 * Description ApiAutoTest
 * Create by qym on 2020/1/9 13:42
 * @author qym
 */
public class UpdateAddFictitiousOrgProductAuth {
    @BeforeMethod(
            description = "虚拟机构产品权限修改"
    )

    public void beforeMethod()
    {
        TestConfig.addOrgProductAuth = ConfigFile.getUrl(InterfaceName.ADDORGPRODUCTAUTH);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "UpdateAddFictitiousOrgProductAuth",description = "虚拟机构产品权限修改")
    public void updateAddFictitiousOrgProductAuth() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.addOrgProductAuth);
        System.out.println(builder);
        String orgId = TokenFile.readFile("E:\\Data\\FictitiousorgId.txt");
        builder.addParameter("insertFlag","false");
        builder.addParameter("orgId",orgId);
        builder.addParameter("platformIds","0,1,2,3,4");
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newValue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newValue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        JSONObject resultJson = new JSONObject(result);
        String  success = (String) resultJson.get("msg");
        Assert.assertEquals("成功",success);
        System.out.println(result);

    }
}
