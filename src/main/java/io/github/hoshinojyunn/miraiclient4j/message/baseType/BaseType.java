package io.github.hoshinojyunn.miraiclient4j.message.baseType;

import cn.hutool.json.JSONObject;

public interface BaseType {
    public abstract JSONObject toJsonObject();
    public abstract String toJsonString();
}
