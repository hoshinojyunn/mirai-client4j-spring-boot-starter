package io.github.hoshinojyunn.miraiclient4j.message;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.github.hoshinojyunn.miraiclient4j.message.baseType.BaseType;

import java.util.*;

public class MessageChain extends LinkedList<JSONObject> {

    public MessageChain appendLast(BaseType baseType){
        JSONObject object = JSONUtil.parseObj(baseType);
        this.addLast(object);
        return this;
    }

    public MessageChain appendFirst(BaseType baseType){
        JSONObject object = JSONUtil.parseObj(baseType);
        this.addFirst(object);
        return this;
    }


}
