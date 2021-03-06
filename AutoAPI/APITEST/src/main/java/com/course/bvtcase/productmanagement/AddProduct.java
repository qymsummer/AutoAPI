package com.course.bvtcase.productmanagement;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.DataIdFile;
import com.course.utils.GetRandom;
import com.course.utils.TokenFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class AddProduct {

    @BeforeTest(
            description = "获取到添加产品的url"
    )
    public void beforeTest() {

        TestConfig.addProductUrl = ConfigFile.getUrl(InterfaceName.ADDPRODUCT);
    }
    @AfterTest
    public void afterTest(){

    }
    @Test(groups = "addProduct",dependsOnGroups = "loginCaseDb",description = "添加产品测试")
    public void runAddProduct() throws Exception {
        String result = addProduct();
        DataIdFile.witerDataId(result);
    }

    public static String addProduct() throws Exception {
        HttpPost httpPost = new HttpPost(TestConfig.addProductUrl);
        JSONObject params = new JSONObject();
        String random= GetRandom.getRandomChar(5);
        String productName= "新账号添加"+random;
        params.put("repaymentMeansIds","9");
        params.put("loanUseIds","1,4");
        params.put("guaranteeMeansIds","5");
        params.put("fullProvince","true");
        params.put("quotaRangeLimitEnum","false");
        params.put("name",productName);
        params.put("termRangeStart","1");
        params.put("termRangeEnd","3");
        params.put("loanUse","信息");
        params.put("annualInterestRangeStart","4");
        params.put("annualInterestRangeEnd","5");
        params.put("quotaRangeEnd","1000");
        params.put("acceptanceTimeStart","6");
        params.put("acceptanceTimeEnd","7");
        params.put("introduce","产品介绍");
        params.put("characteristic","产品特色");
        params.put("applyRequirement","申请条件");
        params.put("requirementMaterial","所需材料");
        params.put("labelIds","24");
        httpPost.setHeader("content-type", "application/json");
        String name="jwtToken";
        String value = TokenFile.readFile();
        httpPost.setHeader(name,value);
        StringEntity entity = new StringEntity(params.toString(),"utf-8");
        httpPost.setEntity(entity);
        String result;
        TestConfig.store = TestConfig.client.getCookieStore();
        List<Cookie> cookieList = TestConfig.store.getCookies();

        TestConfig.client.setCookieStore(TestConfig.store);
        //执行post方法
        HttpResponse response =  TestConfig.client.execute (httpPost);
        //获取响应结果
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println("测试结果"+result);
        JSONObject JSON = new JSONObject(result);
        String success = (String) JSON.get("code");
        //判断
        Assert.assertEquals(0,success);
        String dataId = (String) JSON.get("data");
        return dataId;
    }
}
