package com.wjp.waicoderhelperbackend;

import com.wjp.waicoderhelperbackend.ai.AiCodeHelper;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WAiCoderHelperBackendApplicationTests {

    @Resource
    private AiCodeHelper aiCodeHelper;

    @Test
    void contextLoads() {
    }

    @Test
    void chat() {
        aiCodeHelper.chat("你好，我是程序员鱼皮");
    }

    @Test
    void chatWithMessage() {
        UserMessage userMessage = UserMessage.from(
                TextContent.from("描述图片"),
                ImageContent.from("https://codefather.cn/logo.png")
        );
        aiCodeHelper.chatWithMessage(userMessage);

    }
}
