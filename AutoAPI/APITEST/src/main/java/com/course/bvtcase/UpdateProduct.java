package com.course.bvtcase;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UpdateProduct {
    @BeforeTest(description = "获取到修改产品的url")
    public void beforeTest(){
        TestConfig.updateProductUrl = ConfigFile.getUrl(InterfaceName.UPDATEPRODUCT);
    }
    @AfterTest
    public void afterTest(){
    }
    @Test(dependsOnGroups = "loginCase")
    public void updateProduct() throws IOException {
        HttpPut httpPut = new HttpPut(TestConfig.updateProductUrl);
        String enterpriseAccessRequirementList = "\\[]";
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("id","1171640986992906242");
        map.put("name","试测");
        map.put("provinceId","330000");
        map.put("cityId",null);
        map.put("districtId",null);
        map.put("termRangeStart","1");
        map.put("termRangeEnd","3");
        map.put("annualInterestRangeStart","1");
        map.put("annualInterestRangeEnd","3");
        map.put("quotaRangeEnd","11");
        map.put("acceptanceTimeStart","1");
        map.put("acceptanceTimeEnd","3");
        map.put("loanUseIds","1,4");
        map.put("guaranteeMeansIds","5");
        map.put("repaymentMeansIds","9");
        map.put("introduce","1");
        map.put("characteristic","1");
        map.put("labelIds","");
        map.put("applyRequirementList",null);
        map.put("applyRequirement","1");
        map.put("requirementMaterial","1");
        map.put("saleStatus","0");
        map.put("createTime","2019-09-11 12,25,45");
        map.put("fullProvince",false);
        map.put("applyRequirementIds",new String[] {});
        map.put("enterpriseAccessRequirementList",new String[] {});
        map.put("areaIds",new String[]{"1"});
        //null作为value时，转换成json后不会保存
        JSONObject params = new JSONObject(map);
        System.out.println(params.toString());
        System.out.println(params);
        httpPut.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(params.toString(),"utf-8");
        httpPut.setEntity(entity);
        String result;
        TestConfig.store = TestConfig.client.getCookieStore();
        List<Cookie> cookieList = TestConfig.store.getCookies();
        TestConfig.client.setCookieStore(TestConfig.store);
        //执行put方法
        HttpResponse response =  TestConfig.client.execute (httpPut);
        //获取响应结果
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        /*
        org.json.JSONObject resultJson = new JSONObject(result);
        int  success = (int) resultJson.get("code");//判断
        Assert.assertEquals(0,success);
        System.out.println(result);

         */
    }
}
