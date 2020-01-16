package com.course.bvtcase.orgmanager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/9 11:20
 */

public class FindOrgName {
    @BeforeMethod(
            description = "机构分类查询"
    )
    public void beforeMethod() {
        TestConfig.findOrgName = ConfigFile.getUrl(InterfaceName.FINDORGNAME);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "findOrgName",description = "查找机构")
    public void findOrgName() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.findOrgName);
        String orgTypeName = TokenFile.readFile("E:\\Data\\organizationName.txt");
        String newOrgTyeName = orgTypeName.replaceAll("[\\t\\n\\r\\s]","");
        builder.addParameter("pageNo","1");
        builder.addParameter("pageSize","10");
        builder.addParameter("organizationName",newOrgTyeName);
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newvalue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newvalue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println(result);
        JSONObject resultJson = new JSONObject(result);
        String  success = (String) resultJson.get("msg");
        Assert.assertEquals("成功",success);
        com.alibaba.fastjson.JSONObject jsonpObject = JSON.parseObject(result);
        JSONArray arrs = jsonpObject.getJSONObject("data").getJSONArray("records");
        int a =1;
        if (!jsonpObject.get("code").equals(a)) {
            for (int i = 0; i < arrs.size(); i++) {
                com.alibaba.fastjson.JSONObject obj = arrs.getJSONObject(i);
                String ID1 = obj.getString("orgId");
                TokenFile.witerFile(ID1,"E:\\Data\\OrgID.txt");
                System.out.println(ID1);
            }
        }else {
        }
    }

}
