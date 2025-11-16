package com.chen.aiapi.Service;

// 2. 单个回答类
public class ZhipuChoice {
    private int index;
    private ZhipuMessage message; // 包含回答内容

    // getter 和 setter
    public int getIndex() { return index; }
    public void setIndex(int index) { this.index = index; }
    public ZhipuMessage getMessage() { return message; }
    public void setMessage(ZhipuMessage message) { this.message = message; }
}
