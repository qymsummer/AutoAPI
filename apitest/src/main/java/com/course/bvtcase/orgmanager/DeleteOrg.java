package com.course.bvtcase.orgmanager;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.sql.RunSql;
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
 * Create by qym on 2020/1/16 17:38
 * @author qym
 */
public class DeleteOrg {
    @BeforeMethod(
            description = "删除机构"
    )
    public void beforeMethod() {
        TestConfig.deleteOrg = ConfigFile.getUrl(InterfaceName.DELETEORG);
    }

    @AfterMethod
    public void afterMethod() {
    }

    @Test(groups = "DeleteOrg", description = "删除机构")
    public void deleteOrg() throws IOException, InterruptedException, URISyntaxException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.deleteOrg);
        System.out.println(builder);
        String orgId = TokenFile.readFile("E:\\Data\\OrgID.txt");
        String newOrgId = orgId.replaceAll("[\\t\\n\\r\\s]", "");
        System.out.println(newOrgId);
        builder.addParameter("orgId", newOrgId);
        HttpPost httpPost = new HttpPost(builder.build());
        String name = "jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newValue = value.replaceAll("[\\t\\n\\r\\s]", "");
        httpPost.setHeader(name, newValue);
        HttpResponse response = TestConfig.client.execute(httpPost);
        String result;
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        JSONObject resultJson = new JSONObject(result);
        String success = (String) resultJson.get("msg");
        Assert.assertEquals("成功", success);
        String suc="成功";
        if(success.equals(suc)){
            String str =  TokenFile.readFile("E:\\Data\\OrgID.txt");
            System.out.println(str);
            RunSql.deleteSysOrg(str);
    }
    }
}

