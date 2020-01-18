package com.course.utils;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.IOException;

/**
 * Description ApiAutoTest
 * Create by qym on 2020/1/9 11:20
 * @author qym
 */

public class Ticket {
    @BeforeTest
    public void beforeTest(){
        TestConfig.ticketurl = ConfigFile.getUrl(InterfaceName.GETTICKET);
    }
    @Test
    public void  loginCase() throws IOException {
        HttpPost httpPost = new HttpPost(TestConfig.ticketurl);
        System.out.println(httpPost);
        HttpResponse response = TestConfig.client.execute(httpPost);
        String getTicket;
        getTicket = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = new JSONObject(getTicket);
        JSONObject ticketList = jsonObject.getJSONObject("data");
        String ticket = (String) ticketList.get("ticket");
        System.out.println(ticket);

    }
}
