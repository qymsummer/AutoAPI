package com.course.utils;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class GetTicket {
    @BeforeTest(description = "获取Ticket")
    public void beforeTest(){
        TestConfig.ticketurl = ConfigFile.getUrl(InterfaceName.GETTICKET);
    }
    //public static String json = "{\"msg\":\"成功\",\"code\":\"0\",\"data\":{\"ticket\":\"e9fc0ed4-94a3-4cd3-8259-cb20aba5bdec-1568257800297\"};

    @Test
    public void jsonLoop() throws IOException {
        HttpPost httpPost = new HttpPost(TestConfig.ticketurl);
        HttpResponse response =  TestConfig.client.execute (httpPost);
        String getticket;
        getticket = EntityUtils.toString (response.getEntity(),"utf-8");
        /*
        if(object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            for (Map.Entry<String, Object> entry: jsonObject.entrySet()) {
                Object o = entry.getValue();
                if(o instanceof String) {
                    System.out.println("key:" + entry.getKey() + "，value:" + entry.getValue());
                } else {
                    jsonLoop(o);
                }
            }
        }
    }
         */

        JSONObject resultjson = new JSONObject(getticket);
        System.out.println(resultjson);

    }
}