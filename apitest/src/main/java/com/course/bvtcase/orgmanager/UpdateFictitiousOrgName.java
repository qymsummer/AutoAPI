package com.course.bvtcase.orgmanager;

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
 * Create by qym on 2020/1/9 11:20
 */

public class UpdateFictitiousOrgName {
    @BeforeMethod(
            description = "虚拟机构修改"
    )

    public void beforeMethod()
    {
        TestConfig.addOrUpdate = ConfigFile.getUrl(InterfaceName.ADDORGNAME);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "UpdateFictitiousOrgName",description = "虚拟机构修改")
    public void  updateFictitiousOrgName() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.addOrUpdate);
        String orgTypeId = TokenFile.readFile("E:\\Data\\ID.txt");
        String parentId = TokenFile.readFile("E:\\Data\\OrgID.txt");
        String orgId = TokenFile.readFile("E:\\Data\\FictitiousorgId.txt");
        String organizationName = "英特集团虚拟";
        builder.addParameter("orderNum","");
        builder.addParameter("orgProvinceId","330000");
        builder.addParameter("orgCityId","330100");
        builder.addParameter("orgAreaId","330103");
        builder.addParameter("orgCity","杭州市");
        builder.addParameter("orgArea","下城区");
        builder.addParameter("orgDetail","东新路江南巷2号3幢虚拟");
        builder.addParameter("orgProvince","浙江省");
        builder.addParameter("orgLatitude","44.333333");
        builder.addParameter("orgLongitude","55.333333");
        builder.addParameter("orgTypeId",orgTypeId);
        builder.addParameter("parentId",parentId);
        builder.addParameter("orgSimpleName","英特虚拟");
        builder.addParameter("organizationName",organizationName);
        builder.addParameter("uscId","91330000609120272T");
        builder.addParameter("status","0");
        builder.addParameter("abbreviation","");
        builder.addParameter("ip","");
        builder.addParameter("orgId",orgId);
        builder.addParameter("orgVirtualId",parentId);
        builder.addParameter("orgVirtualFlag","1");
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
        TokenFile.witerFile(organizationName,"E:\\Data\\organizationFictitiouName.txt");
    }
}
