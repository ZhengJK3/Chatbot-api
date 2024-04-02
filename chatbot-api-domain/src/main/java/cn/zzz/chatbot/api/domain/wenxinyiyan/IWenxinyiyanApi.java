package cn.zzz.chatbot.api.domain.wenxinyiyan;

import java.io.IOException;

public interface IWenxinyiyanApi {
    String getAnswer(String access_token, String text) throws IOException;

    String getWenxinToken(String apiKey, String secretKey) throws IOException;

}
