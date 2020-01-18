package com.course.bvtcase.grayscaleorg;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
 * Create by qym on 2020/1/10 11:47
 * @author qym
 */
public class FindGrayScaleOrg {
    @BeforeMethod(
            description = "灰度机构查询"
    )

    public void beforeMethod()
    {

        TestConfig.findGrayOrg = ConfigFile.getUrl(InterfaceName.FINDGRAYORG);
    }
    @AfterMethod
    public void afterMethod(){

    }

    @Test(groups = "FindGrayScaleOrg",description = "灰度机构查询")
    public void findGrayScaleOrg() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.findGrayOrg);
        System.out.println(builder);
        String organizationName = TokenFile.readFile("E:\\Data\\organizationFictitiouName.txt");
        String newOrganizationName = organizationName.replaceAll("[\\t\\n\\r\\s]","");
        builder.addParameter("pageNo", "1");
        builder.addParameter("pageSize", "10");
        builder.addParameter("orgSimpleName", "");
        builder.addParameter("organizationName",newOrganizationName);
        builder.addParameter("platformId","");
        HttpPost httpPost = new HttpPost(builder.build());
        String name = "jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newValue = value.replaceAll("[\\t\\n\\r\\s]", "");
        httpPost.setHeader(name, newValue);
        HttpResponse response = TestConfig.client.execute(httpPost);
        String result;
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject resultJson = new JSONObject(result);
        String success = (String) resultJson.get("msg");

        Assert.assertEquals("成功", success);
        com.alibaba.fastjson.JSONObject jsonpObject = JSON.parseObject(result);
        JSONArray jsonArray = jsonpObject.getJSONObject("data").getJSONArray("records");
        System.out.println(result);
        int code = (int) resultJson.get("code");
        int a =1;
        if (code!=a) {
            for (int i = 0; i < jsonArray.size(); i++) {
                com.alibaba.fastjson.JSONObject obj = jsonArray.getJSONObject(i);
                String id1 = obj.getString("grayId");
                TokenFile.witerFile(id1,"E:\\Data\\GrayId.txt");
                System.out.println(id1);
            }
        }else {
        }
    }
}
