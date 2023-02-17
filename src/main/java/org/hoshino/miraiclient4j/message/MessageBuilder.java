package org.hoshino.miraiclient4j.message;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.hoshino.miraiclient4j.context.ApplicationContextHolder;
import org.hoshino.miraiclient4j.context.MiraiContext;
import org.hoshino.miraiclient4j.message.messageRequest.CommonMessage;
import org.hoshino.miraiclient4j.message.messageRequest.FriendMessage;
import org.hoshino.miraiclient4j.message.messageRequest.GroupMessage;
import org.hoshino.miraiclient4j.message.messageRequest.TempMessage;
import org.springframework.util.Assert;

public class MessageBuilder {

    private static String sessionKey;
    static {
        MiraiContext context = ApplicationContextHolder.getBean(MiraiContext.class).orElse(null);
        Assert.notNull(context, "miraiContext session must be not null when built a message");
        sessionKey = context.getSession();
    }

    public static FriendMessage buildFriendMessage(long target, MessageChain messages){
        FriendMessage friendMessage = new FriendMessage(target, messages);
        friendMessage.setSessionKey(sessionKey);
        return friendMessage;
    }

    public static GroupMessage buildGroupMessage(long target, MessageChain messages){
        GroupMessage groupMessage = new GroupMessage(target, messages);
        groupMessage.setSessionKey(sessionKey);
        return groupMessage;
    }


    public static CommonMessage buildMessage(long target, MessageChain messages){
        CommonMessage commonMessage = new CommonMessage(target, messages);
        commonMessage.setSessionKey(sessionKey);
        return commonMessage;
    }

    public static TempMessage buildTempMessage(Long target, MessageChain messages){
        TempMessage tempMessage = new TempMessage(target, null, messages);
        tempMessage.setSessionKey(sessionKey);
        return tempMessage;
    }




}
