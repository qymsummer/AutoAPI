package com.course.bvtcase.menumanager;

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
 * Create by qym on 2020/1/13 11:32
 */
public class FindMenu {
    @BeforeMethod(
            description = "菜单查询"
    )
    public void beforeMethod() {
        TestConfig.findMenu = ConfigFile.getUrl(InterfaceName.FINDMENU);
    }
    @AfterMethod
    public void afterMethod(){

    }

    @Test(groups = "FindMenu",description = "菜单查询")
    public void findMenu() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.findMenu);
        String menuName = TokenFile.readFile("E:\\Data\\menuname.txt");
        String newMenuName = menuName.replaceAll("[\\t\\n\\r\\s]","");
        builder.addParameter("pageNo","1");
        builder.addParameter("pageSize","10");
        builder.addParameter("menuName",newMenuName);
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newvalue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newvalue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        JSONObject resultJson = new JSONObject(result);
        String  success = (String) resultJson.get("msg");
        Assert.assertEquals("成功",success);
        com.alibaba.fastjson.JSONObject jsonpObject = JSON.parseObject(result);
        JSONArray arrs = jsonpObject.getJSONObject("data").getJSONArray("records");
        System.out.println(result);
        int a =1;
        if (!jsonpObject.get("code").equals(a)) {
            for (int i = 0; i < arrs.size(); i++) {
                com.alibaba.fastjson.JSONObject obj = arrs.getJSONObject(i);
                String ID1 = obj.getString("menuId");
                TokenFile.witerFile(ID1,"E:\\Data\\menuId.txt");
                System.out.println(ID1);
            }
        }else {
        }
    }
}
