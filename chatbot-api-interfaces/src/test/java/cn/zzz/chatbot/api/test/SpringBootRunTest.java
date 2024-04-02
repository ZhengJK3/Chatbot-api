package cn.zzz.chatbot.api.test;

import cn.zzz.chatbot.api.domain.wenxinyiyan.IWenxinyiyanApi;
import cn.zzz.chatbot.api.domain.zsxq.IZsxqApi;
import cn.zzz.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.zzz.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author 小傅哥，微信：fustack
 * @description
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Value("${chatbot-api.apiKey}")
    private String apiKey;
    @Value("${chatbot-api.secretKey}")
    private String secretKey;

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IWenxinyiyanApi wenxinyiyanApi;

    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("topicId：{} text：{}", topicId, text);

            String token = wenxinyiyanApi.getWenxinToken(apiKey, secretKey);
            logger.info("测试结果。 token: " + token);
            String result = wenxinyiyanApi.getAnswer(token, "帮我写一个java冒泡排序");
            logger.info("测试结果。 result:" + result);

            // 回答问题
            zsxqApi.answer(groupId, cookie, topicId, result, false);
        }
    }

    @Test
    public void test_wenxinyiyan() throws IOException{
        String token = wenxinyiyanApi.getWenxinToken(apiKey, secretKey);
        logger.info("测试结果。 token: " + token);
        String result = wenxinyiyanApi.getAnswer(token, "帮我写一个java冒泡排序");
        logger.info("测试结果。 result:" + result);
    }


}
