package com.course.other;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();


        map.put("applyRequirementIds",new String[] {});
        map.put("enterpriseAccessRequirementList",new String [] {"code"+":"+"11"});

        map.put("areaIds",new String[]{"1"});
        //null作为value时，转换成json后不会保存


        JSONObject params = new JSONObject(map);

        System.out.println(params.toString());

    }
}
