package cn.zzz.chatbot.api.test.wenxinyiyan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.junit.Test;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class ChineseGPT {
    final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    @Test
    public void gpttest() throws IOException{
        //1、获取token
        String access_token = new ChineseGPT().getWenxinToken();
        //2、访问数据
        String requestMethod = "POST";
        String body = URLEncoder.encode("junshi","UTF-8");//设置要传的信息

        String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions?access_token="+access_token;//post请求时格式
//        String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/eb-instant?access_token="+access_token;//post请求时格式
        //测试：访问聚合数据的地区新闻api
        HashMap<String, String> msg = new HashMap<>();
        msg.put("role","user");
        msg.put("content", "用java写一个冒泡排序");
        ArrayList<HashMap> messages = new ArrayList<>();
        messages.add(msg);
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("messages", messages);
        String outputStr = JSON.toJSONString(requestBody);
        JSON json = HttpRequest.httpRequest(url,requestMethod,outputStr,"application/json");
        System.out.println(json);
    }

    public String getWenxinToken() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        String apiKey = "1Cf8wPo1vHNsleQwk9JI9GdH";
        String secretKey = "aUhWsDtyFTkdBayLP4PVTsIiB4SFzWdP";
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token?client_id=" + apiKey + "&client_secret=" + secretKey + "&grant_type=client_credentials") //按官网要求填写你申请的key和相关秘钥
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        String s = response.body().string();
        JSONObject objects = JSONArray.parseObject(s);
        String msg = objects.getString("access_token");
        System.out.println(msg);
        return msg;
    }
}