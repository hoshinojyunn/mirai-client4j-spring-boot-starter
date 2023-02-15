package org.hoshino.miraiclient4j.message;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.hoshino.miraiclient4j.context.ApplicationContextHolder;
import org.hoshino.miraiclient4j.context.MiraiContext;
import org.hoshino.miraiclient4j.message.messageRequest.FriendMessage;
import org.springframework.util.Assert;

public class MessageBuilder {

    private static String sessionKey;
    static {
        MiraiContext context = ApplicationContextHolder.getBean(MiraiContext.class).orElse(null);
        Assert.notNull(context, "miraiContext session must be not null when built a message");
        sessionKey = context.getSession();
    }

    public static FriendMessage buildFriendMessage(long target, MessageChain messages){
        JSONObject entries = new JSONObject();
        entries.set("sessionKey", sessionKey);
        entries.set("target", target);
        entries.set("messageChain", messages);
        return JSONUtil.toBean(entries, FriendMessage.class);
    }



}
