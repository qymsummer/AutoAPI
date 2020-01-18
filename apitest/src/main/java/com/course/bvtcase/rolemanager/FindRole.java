package com.course.bvtcase.rolemanager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.GetRandom;
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
 * Create by qym on 2020/1/9 16:51
 * @author qym
 */
public class FindRole {
    @BeforeMethod(
            description = "查询角色"
    )

    public void beforeMethod()
    {
        TestConfig.findRoleList = ConfigFile.getUrl(InterfaceName.FINDROLELIST);
    }
    @AfterMethod
    public void afterMethod(){

    }

    @Test(groups = "FindRole",description = "查询角色")
    public void findRole() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.findRoleList);
        System.out.println(builder);
        String roleName = TokenFile.readFile("E:\\Data\\RoleName.txt");
        String newRoleName = roleName.replaceAll("[\\t\\n\\r\\s]","");
        builder.addParameter("pageNo", "1");
        builder.addParameter("pageSize", "10");
        builder.addParameter("roleName",newRoleName);
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
                String id1 = obj.getString("roleId");
                TokenFile.witerFile(id1,"E:\\Data\\RoleId.txt");
                System.out.println(id1);
            }
        }else {
        }
    }
}
