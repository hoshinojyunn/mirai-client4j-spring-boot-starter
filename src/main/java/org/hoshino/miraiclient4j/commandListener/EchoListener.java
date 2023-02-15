package org.hoshino.miraiclient4j.commandListener;

import cn.hutool.json.JSONObject;
import org.hoshino.miraiclient4j.aspect.annotation.CommandListener;
import org.hoshino.miraiclient4j.aspect.annotation.OnCommand;
import org.hoshino.miraiclient4j.bot.Bot;
import org.hoshino.miraiclient4j.message.MessageBuilder;
import org.hoshino.miraiclient4j.message.MessageChain;
import org.hoshino.miraiclient4j.message.baseType.Plain;
import org.hoshino.miraiclient4j.message.messageChainType.MessageEvent;
import org.hoshino.miraiclient4j.message.messageRequest.FriendMessage;
import org.hoshino.miraiclient4j.utils.MessageUtil;

import javax.annotation.Resource;

@CommandListener
public class EchoListener {
    @Resource
    private Bot bot;

    @OnCommand(command = "/echo")
    public void echo(MessageEvent message){
        String body = MessageUtil.parseBody(message);
        Long target = MessageUtil.getSenderId(message);
        FriendMessage friendMessage = MessageBuilder.buildFriendMessage(target, new MessageChain().append(new Plain(body)));
        bot.sendToFriend(friendMessage);
    }
}
