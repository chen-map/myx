package com.chen.aiapi.Controller;
import com.chen.aiapi.Service.ZhipuAiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AiController {
    private final ZhipuAiService zhipuAiService;

    public AiController(ZhipuAiService zhipuAiService) {
        this.zhipuAiService = zhipuAiService;
    }

    // 接收用户输入，返回 AI 回答
    @PostMapping("/chat")
    public String chatWithAi(@RequestBody Map<String, String> request) throws Exception {
        // request 需包含 "userInput"（用户输入）和 "model"（模型名称）
        return zhipuAiService.chatWithZhipu(
                request.get("userInput"),
                request.get("model")
        );
    }
}