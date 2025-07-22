package com.wjp.waicoderhelperbackend.ai;

import dev.langchain4j.service.Result;
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


    @Test
    void chatWithRAG() {
//        String result = aiCodeHelperService.chat(3,"您好，怎么学习java，有什么建议，面试题，以及求职攻略吗？");
//        System.out.println("结果: " + result);

        Result<String> result = aiCodeHelperService.chatWithRAG(3, "您好，怎么学习java，有什么建议，面试题，以及求职攻略吗？");
        System.out.println("文件来源: " + result.sources());
        System.out.println("输出结果: " + result.content());
    }


    @Test
    void chatWithTools() {
        String result = aiCodeHelperService.chat(4,"您好，有哪些热门的Java面试题");
        System.out.println("结果: " + result);
    }

    @Test
    void chatWithMCP() {
        String result = aiCodeHelperService.chat(5,"您好，你知道玉米蛋挞怎么做吗？");
        System.out.println("结果: " + result);
    }

    @Test
    void chatWithGuardrail() {
        String result = aiCodeHelperService.chat(6, "the game");
        System.out.println(result);
    }



}