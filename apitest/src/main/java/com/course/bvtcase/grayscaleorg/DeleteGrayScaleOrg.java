package com.course.bvtcase.grayscaleorg;

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
 * @Description ApiAutoTest
 * @Date 2020/1/17 17:10
 * @Author qym
 */
public class DeleteGrayScaleOrg {
    @BeforeMethod(
            description = "灰度机构删除"
    )

    public void beforeMethod() {
        TestConfig.deleteGrayOrg = ConfigFile.getUrl(InterfaceName.DELETEGRAYORG);
    }
    @AfterMethod
    public void afterMethod(){
    }
    @Test(groups = "DeleteGrayScaleOrg",description = "灰度机构删除")
    public void deleteGrayScaleOrg() throws URISyntaxException, IOException, InterruptedException {
        Thread.sleep(100);
        URIBuilder builder = new URIBuilder(TestConfig.deleteGrayOrg);
        System.out.println(builder);
        String grayId = TokenFile.readFile("E:\\Data\\GrayId.txt");
        String newGrayId = grayId.replaceAll("[\\t\\n\\r\\s]","");
        builder.addParameter("grayId",newGrayId);
        HttpPost httpPost = new HttpPost(builder.build());
        String name="jwtToken";
        String value = TokenFile.readFile("E:\\Data\\Tokenfile.txt");
        String newValue = value.replaceAll("[\\t\\n\\r\\s]","");
        httpPost.setHeader(name,newValue);
        HttpResponse response = TestConfig.client.execute (httpPost);
        System.out.println(response);
        String result;
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println(result);
        JSONObject resultJson = new JSONObject(result);
        String  success = (String) resultJson.get("msg");
        Assert.assertEquals("成功",success);
    }
}
