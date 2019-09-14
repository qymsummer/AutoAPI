package com.course.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
public class Json {
    private static void strWritedToJSONObject() {
        String myJsonObj = "{\n" +
                "    \"msg\":\"成功\",\n" +
                "    \"code\":0,\n" +
                "    \"data\": {\n" +
                "        \"ticket\":\"f3bfa7ce-4cdb-4e22-95a4-a8addcabd15c-1568259476432\",\n" +
                "    }\n" +
                "}";

            JSONObject jsonobj = JSON.parseObject(myJsonObj); //将json字符串转换成jsonObject对象
            /***获取JSONObject中每个key对应的value值时，可以根据实际场景中想得到什么类型就分别运用不到的方法***/

            System.out.println(jsonobj.getJSONObject("data")); //取出sites对应的value值，得到一个JSONObject子对象
        }
    public static void main(String[] args) {
        strWritedToJSONObject();

    }
}
