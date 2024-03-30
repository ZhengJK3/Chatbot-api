package cn.zzz.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;


public class ApiTest {
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 新建一个get请求
        HttpGet httpGet = new HttpGet("https://api.zsxq.com/v2/groups/48888851514558/topics?scope=unanswered_questions&count=20");
        // 添加cookie和Content-Type
        httpGet.addHeader("cookie", "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218b62337fa949d-0ccfe3e81ee711-26031151-1327104-18b62337faacd6%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThiNjIzMzdmYTk0OWQtMGNjZmUzZTgxZWU3MTEtMjYwMzExNTEtMTMyNzEwNC0xOGI2MjMzN2ZhYWNkNiJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218b62337fa949d-0ccfe3e81ee711-26031151-1327104-18b62337faacd6%22%7D; zsxq_access_token=2FEE45DD-DDFD-31EA-EC75-D129FD449EFA_C3C0AAE93EAFA26F; abtest_env=product; zsxqsessionid=13a33ac8375a7cc7621a79347399f569");
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
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/1522584251815522/answer");

        // 添加cookie和Content-Type
        post.addHeader("cookie", "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218b62337fa949d-0ccfe3e81ee711-26031151-1327104-18b62337faacd6%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThiNjIzMzdmYTk0OWQtMGNjZmUzZTgxZWU3MTEtMjYwMzExNTEtMTMyNzEwNC0xOGI2MjMzN2ZhYWNkNiJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218b62337fa949d-0ccfe3e81ee711-26031151-1327104-18b62337faacd6%22%7D; zsxq_access_token=2FEE45DD-DDFD-31EA-EC75-D129FD449EFA_C3C0AAE93EAFA26F; abtest_env=product; zsxqsessionid=13a33ac8375a7cc7621a79347399f569");
        post.addHeader("Content-Type", "application/json, text/plain");
        // 回答内容
        String ans = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"好啊好\\n\",\n" +
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

}
