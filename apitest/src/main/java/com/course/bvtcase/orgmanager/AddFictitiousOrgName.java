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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/9 11:20
 * @author qym
 */

public class AddFictitiousOrgName {
    @BeforeMethod(
            description = "新增虚拟机构"
    )

    public void beforeMethod() {
        TestConfig.addOrgName = ConfigFile.getUrl(InterfaceName.ADDORGNAME);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "addFictitiousOrgName",description = "新增虚拟机构")
    public void addFictitiousOrgName() throws URISyntaxException, Exception {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.addOrgName);
        String orgTypeId = TokenFile.readFile("E:\\Data\\ID.txt");
        String orgVirtualId = TokenFile.readFile("E:\\Data\\OrgID.txt");
        String organizationName = "英特集团股份有限公司虚拟";
        builder.addParameter("orgVirtualFlag","1");
        builder.addParameter("orgVirtualId",orgVirtualId);
        builder.addParameter("parentId",orgVirtualId);
        builder.addParameter("orgDetail","东新路江南巷2号4幢虚拟");
        builder.addParameter("orgSimpleName","浙江英特有限公司虚拟");
        builder.addParameter("organizationName",organizationName);
        builder.addParameter("uscId","91330000609120272T");
        builder.addParameter("orgProvince","浙江省");
        builder.addParameter("orgProvinceId","330000");
        builder.addParameter("status","0");
        builder.addParameter("orgLongitude","22.333333");
        builder.addParameter("orgLatitude","22.333333");
        builder.addParameter("orgTypeId",orgTypeId);
        builder.addParameter("orgCityId","330200");
        builder.addParameter("orgAreaId","330205");
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

        TokenFile.witerFile(organizationName,"E:\\Data\\FictitiousorganizationName.txt");

    }
}
