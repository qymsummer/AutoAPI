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
 */

public class AddOrgName {
    @BeforeMethod(
            description = "新增机构"
    )
    public void beforeMethod() {
        TestConfig.addOrgName = ConfigFile.getUrl(InterfaceName.ADDORGNAME);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "addOrgName",description = "新增机构")
    public void addOrgName() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.addOrgName);
        String orgTypeId = TokenFile.readFile("E:\\Data\\ID.txt");
        System.out.println(orgTypeId);
        String organizationName = "浙江英特集团股份有限公司";
        builder.addParameter("orderNum","11");
        builder.addParameter("orgProvinceId","330000");
        builder.addParameter("orgProvince","浙江省");
        builder.addParameter("orgCityId","330200");
        builder.addParameter("orgAreaId","330205");
        builder.addParameter("orgDetail","东新路江南巷2号4幢");
        builder.addParameter("orgCity","宁波市");
        builder.addParameter("orgArea","江北区");
        builder.addParameter("parentId","8888");
        builder.addParameter("organizationName",organizationName);
        builder.addParameter("status","0");
        builder.addParameter("uscId","91330000609120272T");
        builder.addParameter("orgSimpleName","浙江英特");
        builder.addParameter("orgLongitude","11.333333");
        builder.addParameter("orgLatitude","11.333333");
        builder.addParameter("orgTypeId",orgTypeId);
        builder.addParameter("abbreviation","ZJYT");
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
        TokenFile.witerFile(organizationName,"E:\\Data\\organizationName.txt");
    }
}
