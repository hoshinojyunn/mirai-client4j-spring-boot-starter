package org.hoshino.miraiclient4j.message;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.hoshino.miraiclient4j.message.baseType.AbstractType;
import org.hoshino.miraiclient4j.message.baseType.BaseType;

import java.util.*;

public class MessageChain extends LinkedList<JSONObject> {

    public MessageChain append(BaseType baseType){
        JSONObject object = JSONUtil.parseObj(baseType);
        this.addLast(object);
        return this;
    }


}
