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
    public void beforeTest() {
        TestConfig.ticketurl = ConfigFile.getUrl(InterfaceName.GETTICKET);
    }
    @Test
    public void jsonLoop() throws IOException {
        HttpPost httpPost = new HttpPost(TestConfig.ticketurl);
        HttpResponse response = TestConfig.client.execute(httpPost);
        String getticket;
        getticket = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject resultjson = new JSONObject(getticket);
        JSONObject S = resultjson.getJSONObject("data");
        System.out.println( S.get("ticket"));

    }
}

