package com.course.bvtcase.login;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.TokenFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class DistriButorLoginOut {
    @BeforeTest
    public void beforeTest() {
        TestConfig.loginOut = ConfigFile.getUrl(InterfaceName.LOGINOUT);
    }

    @AfterTest
    public void afterTest() {

    }

    @Test(groups="loginOut",dependsOnGroups = "loginCaseDb",description = "用户退出登录")
    public void loginOut() throws Exception {
        HttpPost httpPost = new HttpPost(TestConfig.loginOut);
        String name="jwtToken";
        String value = TokenFile.readFile();
        httpPost.setHeader(name,value);
        String result;
        //设置Cookies信
        TestConfig.client.setCookieStore(TestConfig.store);
        TestConfig.store = TestConfig.client.getCookieStore();
        List<Cookie> cookieList = TestConfig.store.getCookies();
        //执行post方法
        HttpResponse response = TestConfig.client.execute (httpPost);
        //获取响应结果
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        JSONObject resultJson = new JSONObject (result);
        //int success = (int) resultJson.get("code");
        //判断
        //Assert.assertEquals(0,success);

        System.out.println(result);
    }
}
