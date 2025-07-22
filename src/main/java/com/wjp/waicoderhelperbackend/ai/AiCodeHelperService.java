package com.wjp.waicoderhelperbackend.ai;

import com.wjp.waicoderhelperbackend.ai.guardrail.SafeInputGuardrail;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.guardrail.InputGuardrails;

import java.util.List;

/**
 * AI Service (简化操作，相比于 ChatModel)
 */
@InputGuardrails({SafeInputGuardrail.class}) // 输入护轨注解
public interface AiCodeHelperService {

    // 指定本地文件
    @SystemMessage(fromResource = "system-prompt.txt")
    /**
     * 聊天
     * @param memoryId 添加注解，进行消息的隔离
     */
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);

    // 让AI输出 report对象
    // 指定本地文件
    @SystemMessage(fromResource = "system-prompt.txt")
    Report chatForReport(@UserMessage String userMessage);

    // AI 生成一个学习报告 record类型[写方法一样来定义类]
    record Report(String name, List<String> suggestionList) {
    }


    // 返回封装后的结果【token消耗数、数据来源、返回结果】
    // 指定本地文件
    @SystemMessage(fromResource = "system-prompt.txt")
    Result<String> chatWithRAG(@MemoryId int memoryId, @UserMessage String userMessage);

}
