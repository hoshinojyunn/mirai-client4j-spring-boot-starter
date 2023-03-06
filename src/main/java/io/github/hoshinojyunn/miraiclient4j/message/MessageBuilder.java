package io.github.hoshinojyunn.miraiclient4j.message;

import io.github.hoshinojyunn.miraiclient4j.context.ApplicationContextHolder;
import io.github.hoshinojyunn.miraiclient4j.context.MiraiContext;
import io.github.hoshinojyunn.miraiclient4j.message.messageRequest.request.*;
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

    public static NudgeMessage buildNudgeMessage(Long target, Long subject, String kind){
        NudgeMessage nudgeMessage = new NudgeMessage(target, subject, kind);
        nudgeMessage.setSessionKey(sessionKey);
        return nudgeMessage;
    }

    public static RecallMessage buildRecallMessage(Long target, Integer messageId){
        RecallMessage recallMessage = new RecallMessage(target, messageId);
        recallMessage.setSessionKey(sessionKey);
        return recallMessage;
    }









}
