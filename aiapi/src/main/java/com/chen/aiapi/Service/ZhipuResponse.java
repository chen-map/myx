package com.chen.aiapi.Service;
// 1. 顶级响应类
public class ZhipuResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private java.util.List<ZhipuChoice> choices; // 关键：choices 是 List
    private ZhipuUsage usage;

    // getter 和 setter（必须有，否则 Jackson 无法解析）
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getObject() { return object; }
    public void setObject(String object) { this.object = object; }
    public long getCreated() { return created; }
    public void setCreated(long created) { this.created = created; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public java.util.List<ZhipuChoice> getChoices() { return choices; }
    public void setChoices(java.util.List<ZhipuChoice> choices) { this.choices = choices; }
    public ZhipuUsage getUsage() { return usage; }
    public void setUsage(ZhipuUsage usage) { this.usage = usage; }
}

