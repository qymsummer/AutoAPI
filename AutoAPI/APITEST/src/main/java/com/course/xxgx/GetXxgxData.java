package com.course.xxgx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.course.config.TestConfig;
import com.course.config.TestConfigXXGX;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFileXXGX;
import com.dataqin.apigateway.sdk.util.Auth;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetXxgxData {
    @BeforeTest(
            description = "获取到查看产品的支行"
    )
    public void beforeTest() {
        TestConfigXXGX.finduri = ConfigFileXXGX.getUrl(InterfaceName.FIND);
        System.out.println(TestConfigXXGX.finduri);
    }
    @AfterTest
    public void afterTest(){
    }
    @Test(groups = "lookProduct",description = "查看产品详情测试")
    public void lookPud() throws IOException, InterruptedException {
        List<String> list = getList();
        for(String temp : list) {
            Thread.currentThread().sleep(10);
            HttpPost httpPost = new HttpPost(TestConfigXXGX.finduri);
            JSONObject params = new JSONObject();
            params.put("identificationId", temp);
            httpPost.setHeader("content-type", "application/json");
            String url = "/interface/v1/business/basicenterpriseinformation";
            Auth auth = Auth.create("qppfa2l82fum", "tacnarye1i5se2tar9a5iqyykpbl3nf8");
            String signature = auth.getSign(url);
            httpPost.setHeader("Signature", signature);
            StringEntity entity = new StringEntity(params.toString(), "utf-8");
            httpPost.setEntity(entity);
            String result;

            //执行post方法
            HttpResponse response = TestConfig.client.execute(httpPost);
            //获取响应结果
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            com.alibaba.fastjson.JSONObject jsonpObject = JSON.parseObject(result);
            //System.out.println(jsonpObject);

            int a =1;
            if (!jsonpObject.get("code").equals(a)){
                //System.out.println(jsonpObject.getJSONObject("data").getJSONArray("datas"));
                JSONArray arrs = jsonpObject.getJSONObject("data").getJSONArray("datas");
                for (int i = 0; i < arrs.size(); i++) {
                    com.alibaba.fastjson.JSONObject obj = arrs.getJSONObject(i);
                    System.out.print(obj.getString("entname"));
                    System.out.print(",");
                    System.out.print(obj.getString("uniscid"));
                }
            } else {
                System.out.print("错误的数据:"+temp);
            }
            System.out.println();
            System.out.println(temp);
            }
        }
    private List getList() {
        List<String> list = new ArrayList<String>();
        list.add("91330881794354707B");
        return list;
    }
}
