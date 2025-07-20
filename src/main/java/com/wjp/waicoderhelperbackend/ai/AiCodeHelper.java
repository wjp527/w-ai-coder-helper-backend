package com.wjp.waicoderhelperbackend.ai;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AiCodeHelper {
    /**
     * 阿里的模型
     */
    @Resource
    private ChatModel qwenChatModel;

    public String chat(String message) {
        // 将消息封装为 userMessage
        UserMessage userMessage = UserMessage.from(message);
        // 传入给模型
        ChatResponse chatResponse = qwenChatModel.chat(userMessage);
        // 从AI中取出回复结果
        AiMessage aiMessage = chatResponse.aiMessage();
        System.out.println("AI 输出:" + aiMessage.toString());
        return aiMessage.text();
    }

}
