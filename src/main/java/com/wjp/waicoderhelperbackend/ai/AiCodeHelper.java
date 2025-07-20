package com.wjp.waicoderhelperbackend.ai;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
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

    /**
     * 系统预设
     */
    private static final String SYSTEM_MESSAGE = """
            你是编程领域的小助手，帮助用户解答编程学习和求职面试相关的问题，并给出建议。重点关注 4 个方向：
            1. 规划清晰的编程学习路线
            2. 提供项目学习建议
            3. 给出程序员求职全流程指南（比如简历优化、投递技巧）
            4. 分享高频面试题和面试技巧
            请用简洁易懂的语言回答，助力用户高效学习与求职。
            """;

    /**
     * 基础对话 (文本)
     * @param message
     * @return
     */
    public String chat(String message) {
        // 系统预设
        SystemMessage systemMessage = SystemMessage.from(SYSTEM_MESSAGE);
        // 将消息封装为 userMessage
        UserMessage userMessage = UserMessage.from(message);
        // 传入给模型
        ChatResponse chatResponse = qwenChatModel.chat(systemMessage, userMessage);
        // 从AI中取出回复结果
        AiMessage aiMessage = chatResponse.aiMessage();
        System.out.println("AI 输出:" + aiMessage.toString());
        return aiMessage.text();
    }

    /**
     * 自定义用户消息 (多模态)
     * @return
     */
    public String chatWithMessage(UserMessage userMessage) {
        // 传入给模型
        ChatResponse chatResponse = qwenChatModel.chat(userMessage);
        // 从AI中取出回复结果
        AiMessage aiMessage = chatResponse.aiMessage();
        System.out.println("AI 输出:" + aiMessage.toString());
        return aiMessage.text();
    }



}
