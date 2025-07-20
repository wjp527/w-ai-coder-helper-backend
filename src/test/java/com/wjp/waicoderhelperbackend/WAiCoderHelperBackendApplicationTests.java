package com.wjp.waicoderhelperbackend;

import com.wjp.waicoderhelperbackend.ai.AiCodeHelper;
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
}
