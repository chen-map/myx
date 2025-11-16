package com.chen.aiapi.Service;

// 3. 消息内容类
public class ZhipuMessage {
    private String role;
    private String content; // AI 回答的文本

    // getter 和 setter
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
