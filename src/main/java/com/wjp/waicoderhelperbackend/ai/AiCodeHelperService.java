package com.wjp.waicoderhelperbackend.ai;

import dev.langchain4j.service.SystemMessage;

/**
 * AI Service (简化操作，相比于 ChatModel)
 */
public interface AiCodeHelperService {

    // 指定本地文件
    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(String userMessage);
}
