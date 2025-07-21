package com.wjp.waicoderhelperbackend.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

/**
 * AI Service (简化操作，相比于 ChatModel)
 */
public interface AiCodeHelperService {

    // 指定本地文件
    @SystemMessage(fromResource = "system-prompt.txt")
    /**
     * 聊天
     * @param memoryId 添加注解，进行消息的隔离
     */
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);
}
