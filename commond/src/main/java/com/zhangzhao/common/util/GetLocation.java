package com.zhangzhao.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;

@Slf4j
public class GetLocation {

    public static String[] getAdd(String log, String lat ){
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line+"\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
        JSONObject jsonObject = JSONObject.parseObject(res);
        JSONArray jsonArray = jsonObject.getJSONArray("addrList");
        JSONObject j_2 = JSONObject.parseObject(jsonArray.get(0).toString());
        String allAdd = j_2.getString("admName");
        return allAdd.split(",");
    }
}
