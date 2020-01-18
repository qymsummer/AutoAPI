package com.course.bvtcase.orgmanager;

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
 * Create by qym on 2020/1/9 11:20
 * @author qym
 */
public class FindFictitiousOrgName {
    @BeforeMethod(
            description = "虚拟机构查询"
    )
    public void beforeMethod() {
        TestConfig.findOrgName = ConfigFile.getUrl(InterfaceName.FINDORGNAME);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "FindFictitiousOrgName",description = "虚拟机构查询")
    public void findFictitiousOrgName() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.findOrgName);
        String orgTypeName = TokenFile.readFile("E:\\Data\\FictitiousorganizationName.txt");
        String newOrgTyeName = orgTypeName.replaceAll("[\\t\\n\\r\\s]","");
        builder.addParameter("pageNo","1");
        builder.addParameter("pageSize","10");
        builder.addParameter("organizationName",newOrgTyeName);
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
        int code = (int) resultJson.get("code");
        System.out.println(code);
        Assert.assertEquals("成功",success);
        com.alibaba.fastjson.JSONObject jsonpObject = JSON.parseObject(result);
        JSONArray arrs = jsonpObject.getJSONObject("data").getJSONArray("records");
        int a =1;
        if (code!=a) {
            for (int i = 0; i < arrs.size(); i++) {
                com.alibaba.fastjson.JSONObject obj = arrs.getJSONObject(i);
                String id1 = obj.getString("orgId");
                TokenFile.witerFile(id1,"E:\\DATA\\FictitiousorgId.txt");
                System.out.println(id1);
            }
        }else {
        }
    }

}
