package com.chen.aiapi.Service;

import com.chen.aiapi.Service.ZhipuResponse; // 注意导入上面的实体类
import com.chen.aiapi.Service.ZhipuChoice;
import com.chen.aiapi.Service.ZhipuMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZhipuAiService {
    private static final Logger log = LoggerFactory.getLogger(ZhipuAiService.class);
    private static final String ZHIPU_API_URL = "https://open.bigmodel.cn/api/paas/v4/chat/completions";
    private final String apiKey = "d71c1d240e814cd28d0f5e1f7ef6ecf2.r5lzBS5mtogdCvTu";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ZhipuAiService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String chatWithZhipu(String userInput, String model) throws Exception {
        // 1. 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // 2. 构建请求体（用 List 存储 messages，避免数组序列化问题）
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model);

        // 关键：messages 用 List 而非数组
        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> userMsg = new HashMap<>();
        userMsg.put("role", "user");
        userMsg.put("content", userInput);
        messages.add(userMsg);
        requestBody.put("messages", messages);

        requestBody.put("temperature", 0.7);

        // 3. 发送请求
        HttpEntity<String> request = new HttpEntity<>(
                objectMapper.writeValueAsString(requestBody),
                headers
        );
        String response = restTemplate.postForObject(ZHIPU_API_URL, request, String.class);
        log.info("智谱 API 响应: {}", response); // 打印响应，方便调试

        // 4. 解析响应（核心修复）
        ZhipuResponse zhipuResponse = objectMapper.readValue(response, ZhipuResponse.class);
        if (zhipuResponse.getChoices() != null && !zhipuResponse.getChoices().isEmpty()) {
            ZhipuChoice choice = zhipuResponse.getChoices().get(0); // 取第一个回答
            return choice.getMessage().getContent(); // 返回 AI 回答内容
        } else {
            return "未获取到 AI 回答";
        }
    }
}