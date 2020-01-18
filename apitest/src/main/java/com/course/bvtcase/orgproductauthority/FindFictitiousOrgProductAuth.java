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
 * Create by qym on 2020/1/9 12:54
 * @author qym
 */
public class FindFictitiousOrgProductAuth {
    @BeforeMethod(
            description = "虚拟机构产品权限查询"
    )

    public void beforeMethod() {
        TestConfig.findOrgProductAuth = ConfigFile.getUrl(InterfaceName.FINDORGPRODUCTAUTH);
    }

    @AfterMethod
    public void afterMethod() {

    }

    @Test(groups = "findFictitiusOrgProductAuth", description = "虚拟机构产品权限查询")
    public void findFictitiusOrgProductAuth() throws InterruptedException, URISyntaxException, IOException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.findOrgProductAuth);
        String organizationName = TokenFile.readFile("E:\\Data\\organizationFictitiouName.txt");
        String newOrgTyeName = organizationName.replaceAll("[\\t\\n\\r\\s]","");
        builder.addParameter("pageNo","1");
        builder.addParameter("pageSize","10");
        builder.addParameter("orgSimpleName","");
        builder.addParameter("organizationName",newOrgTyeName);
        builder.addParameter("platformIds","");
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newValue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newValue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println(result);
        JSONObject resultJson = new JSONObject(result);
        String  success = (String) resultJson.get("msg");
        Assert.assertEquals("成功",success);
    }
}
