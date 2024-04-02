package cn.zzz.chatbot.api.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 新建一个get请求
        HttpGet httpGet = new HttpGet("https://api.zsxq.com/v2/groups/48888851514558/topics?scope=unanswered_questions&count=20");
        // 添加cookie和Content-Type
        httpGet.addHeader("cookie", "zsxqsessionid=6871b7a79d0d4e2d33d7d026917f44ee; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218e87f95b751e2-0f625393d743c7-4c657b58-2073600-18e87f95b76970%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E5%BC%95%E8%8D%90%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fbugstack.cn%2F%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThlODdmOTViNzUxZTItMGY2MjUzOTNkNzQzYzctNGM2NTdiNTgtMjA3MzYwMC0xOGU4N2Y5NWI3Njk3MCJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218e87f95b751e2-0f625393d743c7-4c657b58-2073600-18e87f95b76970%22%7D; zsxq_access_token=0C81CE60-5BF1-33C3-9EE1-29E90BA9207B_C3C0AAE93EAFA26F; tfstk=faHSB09_wabWSHPR_DKqfcNCG3eQw49aJMZKjDBPv8eRJMUj4T7UaDUIRqzmLa-ktS3jzqkEa9yRRW3xfyz8J9IADuqRwuE8woBYbu3Qt34UJygEvb-2QdooqJ2LRFJwQg7eDrg5yWepv-ET2exViTnoqJjmWxaaO0fQBTFAOJ3LHrEUVJEL2kKbMlrLpTFdesQYokLyf6LDN_i2ltXVKhmuL9X4czDbVSdqJrhr1HPzNFozy9Ltyu_LW0Ufp9au0sPKc085ImyoTVZZzKBS5Dni9SMCkFanhmH-9mbWDRijgfPSGUBQuYz0sYe1vLnbFzNtdqvHbVnjvfPj3hpgF8U-T8mFfUojF4oohDSdM8wrG5M7BgJlQPGQy6sbs9Zb7ntft6XNKHZpeEFOC7E0VS-Xcah3woqb7ntft6V8muZwcn_-t; abtest_env=product");
        httpGet.addHeader("Content-Type", "application/json, text/plain");

        // 根据get请求获得请求内容
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 检测请求内容是否正确（200），是输出请求内容，否输出请求结果。
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String s = EntityUtils.toString(response.getEntity());
            System.out.println(s);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 新建一个post请求
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/2855812485144121/answer");

        // 添加cookie和Content-Type
        post.addHeader("cookie", "zsxqsessionid=6871b7a79d0d4e2d33d7d026917f44ee; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218e87f95b751e2-0f625393d743c7-4c657b58-2073600-18e87f95b76970%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E5%BC%95%E8%8D%90%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fbugstack.cn%2F%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThlODdmOTViNzUxZTItMGY2MjUzOTNkNzQzYzctNGM2NTdiNTgtMjA3MzYwMC0xOGU4N2Y5NWI3Njk3MCJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218e87f95b751e2-0f625393d743c7-4c657b58-2073600-18e87f95b76970%22%7D; zsxq_access_token=0C81CE60-5BF1-33C3-9EE1-29E90BA9207B_C3C0AAE93EAFA26F; tfstk=faHSB09_wabWSHPR_DKqfcNCG3eQw49aJMZKjDBPv8eRJMUj4T7UaDUIRqzmLa-ktS3jzqkEa9yRRW3xfyz8J9IADuqRwuE8woBYbu3Qt34UJygEvb-2QdooqJ2LRFJwQg7eDrg5yWepv-ET2exViTnoqJjmWxaaO0fQBTFAOJ3LHrEUVJEL2kKbMlrLpTFdesQYokLyf6LDN_i2ltXVKhmuL9X4czDbVSdqJrhr1HPzNFozy9Ltyu_LW0Ufp9au0sPKc085ImyoTVZZzKBS5Dni9SMCkFanhmH-9mbWDRijgfPSGUBQuYz0sYe1vLnbFzNtdqvHbVnjvfPj3hpgF8U-T8mFfUojF4oohDSdM8wrG5M7BgJlQPGQy6sbs9Zb7ntft6XNKHZpeEFOC7E0VS-Xcah3woqb7ntft6V8muZwcn_-t; abtest_env=product");
        post.addHeader("Content-Type", "application/json, text/plain");
        // 回答内容
        String ans = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"啊\\n\",\n" +
                "    \"image_ids\": []\n" +
                "  }\n" +
                "}";

        // 获得回答内容
        StringEntity stringEntity = new StringEntity(ans, ContentType.create("application/json", "UTF-8"));
        post.setEntity(stringEntity);

        // 根据get请求获得请求内容
        CloseableHttpResponse response = httpClient.execute(post);

        // 检测请求内容是否正确（200），是输出回答内容，否输出回答结果。
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String s = EntityUtils.toString(response.getEntity());
            System.out.println(s);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void wenxinyiyanapi() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //1、获取token
        String access_token = new ApiTest().getWenxinToken();

        HttpPost post = new HttpPost("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions?access_token=" + access_token);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Accept", "application/json");
        String paramJson = "{\"messages\": [{\"role\": \"user\", \"content\": \"帮我写一个冒泡排序\"}]\n}";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("application/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String s = EntityUtils.toString(response.getEntity());
            System.out.println("输出:" + s);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    public String getWenxinToken() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String apiKey = "";
        String secretKey = "";

        HttpPost post = new HttpPost("https://aip.baidubce.com/oauth/2.0/token?client_id=" + apiKey + "&client_secret=" + secretKey + "&grant_type=client_credentials");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Accept", "application/json");
        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String s = EntityUtils.toString(response.getEntity());
            JSONObject objects = JSONArray.parseObject(s);
            String token = objects.getString("access_token");
            System.out.println("token:" + token);
            return token;
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
            return "";
        }
    }

}
