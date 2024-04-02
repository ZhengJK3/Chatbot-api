package cn.zzz.chatbot.api.application.job;

import cn.zzz.chatbot.api.domain.wenxinyiyan.IWenxinyiyanApi;
import cn.zzz.chatbot.api.domain.zsxq.IZsxqApi;
import cn.zzz.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.zzz.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * @program: Chatbot-api
 * @description: 任务
 * @author: chaZ
 * @create: 2024-04-02 16:57
 **/

@EnableScheduling
@Configuration
public class ChatbotSchedule {
    private Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);

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

    @Scheduled(cron = "0/30 * * * * ? ")
    public void run(){
        try {
            if (new Random().nextBoolean()) {
                logger.info("随机打烊中...");
                return;
            }
            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("打烊时间不工作，AI 下班了！");
                return;
            }

            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            logger.info("检索结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (topics == null || topics.isEmpty()) {
                logger.info("本次检索未查到待回答问题。");
                return;
            }

            Topics topic = topics.get(0);
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();

            String token = wenxinyiyanApi.getWenxinToken(apiKey, secretKey);
            String result = wenxinyiyanApi.getAnswer(token, text);

            // 回答问题
            boolean status = zsxqApi.answer(groupId, cookie, topicId, result, false);
            logger.info("编号：{}， 问题：{}， 回答：{}, 状态：{}", topicId, text, result, status);

        } catch (Exception e) {
            logger.error("自动回答异常。", e);
        }
    }
}
