package cn.zzz.chatbot.api.domain.wenxinyiyan.service;

import cn.zzz.chatbot.api.domain.wenxinyiyan.IWenxinyiyanApi;
import cn.zzz.chatbot.api.domain.wenxinyiyan.model.aggregates.AiAnswer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WenxinyiyanApi implements IWenxinyiyanApi {

    private Logger logger = LoggerFactory.getLogger(WenxinyiyanApi.class);


    @Override
    public String getAnswer(String access_token, String text) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions?access_token=" + access_token);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Accept", "application/json");
        String paramJson = "{\"messages\": [{\"role\": \"user\", \"content\": \" " + text + "\"}]\n}";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("application/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答问题结果。 jsonStr" + jsonStr);
            AiAnswer aiAnswer = JSON.parseObject(jsonStr, AiAnswer.class);
            return aiAnswer.getResult();
        } else {
            throw new RuntimeException("aip.baidubce.com Err Code is " + response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public String getWenxinToken(String apiKey, String secretKey) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://aip.baidubce.com/oauth/2.0/token?client_id=" + apiKey + "&client_secret=" + secretKey + "&grant_type=client_credentials");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Accept", "application/json");
        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String s = EntityUtils.toString(response.getEntity());
            JSONObject objects = JSONArray.parseObject(s);
            String token = objects.getString("access_token");
            logger.info("token" + token);
            return token;
        } else {
            throw new RuntimeException("aip.baidubce.com Err Code is " + response.getStatusLine().getStatusCode());
        }
    }
}
