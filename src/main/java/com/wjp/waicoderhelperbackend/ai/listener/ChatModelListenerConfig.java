package com.wjp.waicoderhelperbackend.ai.listener;

import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 监听器
 */
@Configuration
public class ChatModelListenerConfig {
    
    private static final Logger log = LoggerFactory.getLogger(ChatModelListenerConfig.class);
    
    @Bean
    ChatModelListener chatModelListener() {
        return new ChatModelListener() {
            /**
             * 输出请求
             * @param requestContext
             */
            @Override
            public void onRequest(ChatModelRequestContext requestContext) {
                log.info("onRequest(): {}", requestContext.chatRequest());
            }

            /**
             * 输出响应
             * @param responseContext
             */
            @Override
            public void onResponse(ChatModelResponseContext responseContext) {
                log.info("onResponse(): {}", responseContext.chatResponse());
            }

            /**
             * 输出报错
             * @param errorContext
             */
            @Override
            public void onError(ChatModelErrorContext errorContext) {
                log.info("onError(): {}", errorContext.error().getMessage());
            }
        };
    }
}
