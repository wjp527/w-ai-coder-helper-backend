package com.wjp.waicoderhelperbackend.ai;

import com.wjp.waicoderhelperbackend.ai.tools.InterviewQuestionTool;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 创建AI服务
 */
@Configuration // 标记该类为 Spring 配置类，容器会扫描并处理其中的 @Bean 方法
public class AiCodeHelperServiceFactory {

    @Resource
    private ChatModel myQwenChatModel;

    /**
     * 内容加载器
     */
    @Resource
    private ContentRetriever contentRetriever;

    /**
     * MCP工具提供者
     */
    @Resource
    private McpToolProvider mcpToolProvider;

    @Bean
    public AiCodeHelperService aiCodeHelperService() {
        // 会话记忆 限制最多保存10条对话记录
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        // 构造 AI Service
        AiCodeHelperService aiCodeHelperService = AiServices.builder(AiCodeHelperService.class)
                // 模型
                .chatModel(myQwenChatModel)
                // 会话记忆
                .chatMemory(chatMemory)
                // 基于memoryId 进行会话隔离
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                // 设置内容检索器【RAG检索增强生成】
                .contentRetriever(contentRetriever)
                // 工具调用
                .tools(new InterviewQuestionTool())
                // MCP 工具调用
                .toolProvider(mcpToolProvider)
                .build();
        // 创建AI服务的接口类别
        return aiCodeHelperService;
    }
}
