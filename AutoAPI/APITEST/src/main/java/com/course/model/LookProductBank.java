package com.course.model;

import com.course.config.TestConfig;
import com.course.utils.ConfigFileH5;
import com.course.utils.DataIdFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LookProductBank {
    @BeforeTest(
            description = "获取到查看产品的支行"
    )
    public void beforeTest() {

        TestConfig.h5Loign = ConfigFileH5.getUrl(InterfaceName.H5LOGIN);
    }
    @AfterTest
    public void afterTest(){

    }
    @Test(description = "查看产品详情测试")
    public void lookProduct() throws Exception {

        URIBuilder builder = new URIBuilder(TestConfig.h5Loign);
        String dataId = DataIdFile.readFile();
            builder.addParameter("bankId","20768");
            builder.addParameter("districtId","330103");
            HttpGet httpGet = new HttpGet(builder.build());
            String name="gxdjkey";
            String value = "z8D78sNowYZHMg2WKoR2jADR%2Fx%2F6E3De5wXoVjjN39EgVzNIPevJ%2FrrsdZ6tyOn9";
            httpGet.setHeader(name,value);
            HttpResponse response =  TestConfig.client.execute (httpGet);
            String result;
            result = EntityUtils.toString (response.getEntity(),"utf-8");
            System.out.println(dataId);
            System.out.println("测试结果:"+"\t"+result);
        }
    }

