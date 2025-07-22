//package com.wjp.waicoderhelperbackend.ai.model;
//
//import com.wjp.waicoderhelperbackend.ai.listener.ChatModelListenerConfig;
//import dev.langchain4j.community.model.dashscope.QwenChatModel;
//import dev.langchain4j.model.chat.ChatModel;
//import dev.langchain4j.model.chat.listener.ChatModelListener;
//import jakarta.annotation.Resource;
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//// 指定读取要加载的配置文件
//@ConfigurationProperties(prefix = "langchain4j.community.dashscope.chat-model")
//@Data
//public class QwenChatModelConfig {
//    /**
//     * 模型名称
//     */
//    private String modelName;
//
//    /**
//     * apiKey
//     */
//    private String apiKey;
//
//    /**
//     * 监听器
//     */
//    @Resource
//    private ChatModelListener chatModelListener;
//
//    /**
//     * 创建模型
//     * @return
//     */
//    @Bean
//    public ChatModel myQwenChatModel() {
//        QwenChatModel build = QwenChatModel.builder()
//                .apiKey(apiKey)
//                .modelName(modelName)
//                .listeners(List.of(chatModelListener))
//                .build();
//        return build;
//    }
//}
