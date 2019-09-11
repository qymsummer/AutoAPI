package com.course.other;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class LoginAPI {
    @BeforeTest(description = "测试准备工作，获取httpclient对象")
    public void beforeTest(){

        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
    }
    @Test
    public void loginCase() throws UnsupportedEncodingException {
    MultipartEntity entity = new MultipartEntity();
    entity.addPart("param1", new StringBody("中国", Charset.forName("UTF-8")));
    entity.addPart("param2", new StringBody("value2", Charset.forName("UTF-8")));
    }
}
