package org.hoshino.miraiclient4j.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.hoshino.miraiclient4j.message.messageChainType.MessageEvent;

import java.util.Arrays;

public class MessageUtil {

    public static String parseCmd(MessageEvent message){
        JSONArray messageChain = message.getMessageChain();
        if(messageChain.size()<2)
            return "";
        Object o = messageChain.get(1);
        String text = JSONUtil.parseObj(o).getStr("text");
        return text.split(" ")[0];
    }

    public static String parseBody(MessageEvent message){
        JSONArray messageChain = message.getMessageChain();
        if(messageChain.size()<2)
            return "";
        Object o = messageChain.get(1);
        String text = JSONUtil.parseObj(o).getStr("text");
        String[] split = text.split(" ");
        StringBuffer res = new StringBuffer();
        Arrays.stream(split).skip(1).forEach(t-> res.append(t).append(" "));
        return res.toString();
    }

    public static Long getSenderId(MessageEvent messageEvent){
        JSONObject sender = messageEvent.getSender();
        return sender.getLong("id");
    }
    public static String getSenderNickName(MessageEvent messageEvent){
        JSONObject sender = messageEvent.getSender();
        return sender.getStr("nickname");
    }

    public static String getMessageType(MessageEvent messageEvent){
        return messageEvent.getType();
    }

}
