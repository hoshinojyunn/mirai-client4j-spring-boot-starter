package org.hoshino.miraiclient4j.message.baseType;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public abstract class AbstractType implements BaseType {
    private String type;
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    @Override
    public JSONObject toJsonObject() {
        return JSONUtil.parseObj(this);
    }

    @Override
    public String toJsonString() {
        return JSONUtil.toJsonStr(this);
    }
}
