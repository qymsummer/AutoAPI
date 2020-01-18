package com.course.bvtcase.orgmanager;

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
 * @author qym
 */
public class UpdateOrgName {
    @BeforeMethod(
            description = "机构修改"
    )

    public void beforeMethod() {
        TestConfig.addOrUpdate = ConfigFile.getUrl(InterfaceName.ADDORGNAME);
    }
    @AfterMethod
    public void afterMethod(){

    }
    @Test(groups = "UpdateOrgName",description = "机构修改")
    public void  updateOrgName() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.addOrUpdate);
        String orgTypeId = TokenFile.readFile("E:\\Data\\ID.txt");
        String orgId = TokenFile.readFile("E:\\Data\\OrgID.txt");
        String organizationName = TokenFile.readFile("E:\\Data\\organizationName.txt");
        builder.addParameter("orderNum","10");
        builder.addParameter("orgProvinceId","330000");
        builder.addParameter("orgCityId","330100");
        builder.addParameter("orgAreaId","330103");
        builder.addParameter("orgCity","杭州市");
        builder.addParameter("orgArea","下城区");
        builder.addParameter("orgDetail","东新路江南巷2号3幢");
        builder.addParameter("orgProvince","浙江省");
        builder.addParameter("orgLongitude","11.333333");
        builder.addParameter("orgLatitude","11.333333");
        builder.addParameter("orgTypeId",orgTypeId);
        builder.addParameter("parentId","8888");
        builder.addParameter("orgSimpleName","浙江英特有限公司");
        builder.addParameter("organizationName",organizationName);
        builder.addParameter("uscId","91330000609120272T");
        builder.addParameter("status","0");
        builder.addParameter("abbreviation","ZJYT");
        builder.addParameter("orgId",orgId);
        builder.addParameter("orgVirtualFlag","0");
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
