package com.course.h5;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFileH5;
import com.course.utils.DataIdFile;
import com.course.utils.H5TokenFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class fangshandai {
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
        List<String> list = getList();


        for (String temp : list){
            URIBuilder builder = new URIBuilder(TestConfig.h5Loign);
            builder.addParameter("bankId","9928");
            builder.addParameter("districtId",temp);
            HttpGet httpGet = new HttpGet(builder.build());
            String name="gxdjkey";
            String value = H5TokenFile.readFile();
            httpGet.setHeader(name,value);
            HttpResponse response =  TestConfig.client.execute (httpGet);
            String result;
            result = EntityUtils.toString (response.getEntity(),"utf-8");
            System.out.println("银行名称:农村商业银行"+" | "+"产品名称:房闪贷"+" | "+"城市ID:"+temp+"\n"+"返回结果:"+result+"\n");
        }
    }

    private List getList() {
        List<String> list = new ArrayList<String>();
        list.add("330102");
        list.add("330103");
        list.add("330104");
        list.add("330105");
        list.add("330106");
        list.add("330108");
        list.add("330109");
        list.add("330199");
        return list;
    }
}
