package com.course.bvtcase.productmanagement;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.*;
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
    @Test(groups = "updateProduct",dependsOnGroups = "loginCaseDb")
    public void updateProduct() throws Exception {
        String getProductName = Product();
        GetProductName.witerProductName(getProductName);
    }
    public static String Product() throws Exception {
        HttpPut httpPut = new HttpPut(TestConfig.updateProductUrl);
        String random= GetRandom.getRandomChar(7);
        String productName= "修改"+random;
        System.out.println(productName);
        String dataId = DataIdFile.readFile();
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("id",dataId);
        map.put("name",productName);
        map.put("provinceId","330000");
        map.put("cityId",null);
        map.put("districtId",null);
        map.put("termRangeStart","1");
        map.put("termRangeEnd","3");
        map.put("loanUse","企业融资");
        map.put("annualInterestRangeEnd","8.01");
        map.put("annualInterestRangeStart","");
        map.put("quotaRangeEnd","111");
        map.put("acceptanceTimeStart","1");
        map.put("acceptanceTimeEnd","3");
        map.put("loanUseIds","1,4");
        map.put("guaranteeMeansIds","5");
        map.put("repaymentMeansIds","9");
        map.put("introduce","产品介绍");
        map.put("characteristic","产品特色");
        map.put("labelIds","");
        map.put("applyRequirementList",null);
        map.put("applyRequirement","申请条件");
        map.put("requirementMaterial","所需材料");
        map.put("saleStatus","0");
        map.put("createTime","2019-09-11 12,25,45");
        map.put("fullProvince",false);
        map.put("applyRequirementIds",new String[] {});
        map.put("enterpriseAccessRequirementList",new String[] {});
        map.put("areaIds",new String[]{"330199"});
        //null作为value时，转换成json后不会保存
        JSONObject params = new JSONObject(map);
        httpPut.setHeader("content-type", "application/json");
        String name="jwtToken";
        String value = TokenFile.readFile();
        httpPut.setHeader(name,value);
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
        System.out.println("测试结果:"+"\t"+result);
        JSONObject resultJson = new JSONObject(result);
        int  success = (int) resultJson.get("code");//判断
        Assert.assertEquals(0,success);
        return productName;
    }
}
