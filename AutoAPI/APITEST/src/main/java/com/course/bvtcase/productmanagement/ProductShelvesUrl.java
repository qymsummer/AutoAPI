package com.course.bvtcase.productmanagement;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.DataIdFile;
import com.course.utils.TokenFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.cookie.Cookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProductShelvesUrl {

    @BeforeTest(description = "获取到产品上下架的url")
    public void beforeTest(){

        TestConfig.productShelvesUrl = ConfigFile.getUrl(InterfaceName.PRODUCTSHELVES);
    }
    @AfterTest
    public void afterTest(){
    }

    @Test(groups = "productShelvesUrl",dependsOnGroups="LoanApproverloginCase",description = "产品上下架测试")
    public void productShelvesUrl() throws Exception {
        String url = TestConfig.productShelvesUrl;
        String dataId = DataIdFile.readFile();
        System.out.println(dataId);
        String param = ConfigFile.getParams();
        param.replace("\n", "").replace("\r", "");
        String testUrl = url+dataId+param;
        System.out.println(testUrl);
    }
}
