package com.wjp.waicoderhelperbackend.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiCodeHelperServiceTest {

    @Resource
    private AiCodeHelperService aiCodeHelperService;

    @Test
    void chat() {
        String result = aiCodeHelperService.chat(1, "我想吃苹果");
        System.out.println("结果: " + result);
    }

    @Test
    void chatWithMemory() {
        String result = aiCodeHelperService.chat(2,"您好，我是程序员鱼皮");
        System.out.println("结果: " + result);
        result = aiCodeHelperService.chat(2,"您好，我是饿了吗");
        System.out.println("结果: " + result);
    }


    @Test
    void chatFromReport() {
        String userMessage = "你好，我是程序员鱼皮，学编程2年版，请帮我定义学习报告";
        AiCodeHelperService.Report report = aiCodeHelperService.chatForReport(userMessage);
        System.out.println("report: " + report);
    }
}