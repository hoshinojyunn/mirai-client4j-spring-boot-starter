package org.hoshino.miraiclient4j.message.messageChainType;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.hoshino.miraiclient4j.message.baseType.AbstractType;

public class MessageEvent extends AbstractType {
    private JSONObject sender;
    private JSONArray messageChain;

    public MessageEvent(JSONObject sender, JSONArray messageChain) {
        this.sender = sender;
        this.messageChain = messageChain;
    }

    public JSONObject getSender() {
        return sender;
    }

    public void setSender(JSONObject sender) {
        this.sender = sender;
    }

    public JSONArray getMessageChain() {
        return messageChain;
    }

    public void setMessageChain(JSONArray messageChain) {
        this.messageChain = messageChain;
    }
}
