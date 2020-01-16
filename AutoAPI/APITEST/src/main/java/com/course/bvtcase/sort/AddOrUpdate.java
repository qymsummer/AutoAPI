package com.course.bvtcase.sort;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFile;
import com.course.utils.DataIdFile;
import com.course.utils.GetRandom;
import com.course.utils.TokenFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddOrUpdate {
    @BeforeTest(
            description = "获取添加机构分类的URL"
    )
    public void beforeTest() {

        TestConfig.addOrUpdate = ConfigFile.getUrl(InterfaceName.ADDORUPDATE);
    }
    @AfterTest
    public void afterTest(){

    }
    @Test(groups = "addPrUpdate",dependsOnGroups = "loginCaseDb",description = "添加机构分类")
    public void runAddUpdate() throws Exception {
        String result = addUpdate();
        DataIdFile.witerDataId(result);
    }

    public static String addUpdate() throws Exception {
        HttpPost httpPost = new HttpPost(TestConfig.addOrUpdate);
        System.out.println("----------");
        System.out.println(httpPost);
        System.out.println("----------");
        JSONObject params = new JSONObject();
        String random= GetRandom.getRandomChar(5);
        String productName= "AUTOTEST"+random;
        //params.put("id","");
        params.put("parentId","25");
        params.put("orgTypeName","11");//名称
        params.put("orgTypeNo","112");//排序
        params.put("remark","11");//描述
        httpPost.setHeader("content-type", "application/x-www-form-urlencoded");
        String name="jwtToken";
        String value = TokenFile.readFile();
        //System.out.println(value);
        httpPost.setHeader(name,value);
        StringEntity entity = new StringEntity(params.toString(),"utf-8");
        httpPost.setEntity(entity);
        String result;
        //执行post方法
        HttpResponse response =  TestConfig.client.execute (httpPost);
        //获取响应结果
        System.out.println(response);
        result = EntityUtils.toString (response.getEntity(),"utf-8");
        System.out.println("测试结果"+result);

        JSONObject JSON = new JSONObject(result);
        String success = (String) JSON.get("code");
        //判断
        Assert.assertEquals(0,success);
        String dataId = (String) JSON.get("data");
        return dataId;
    }
}
