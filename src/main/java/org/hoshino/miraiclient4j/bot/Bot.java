package org.hoshino.miraiclient4j.bot;


import org.hoshino.miraiclient4j.adapter.HttpApiClient;
import org.hoshino.miraiclient4j.message.MessageBuilder;
import org.hoshino.miraiclient4j.message.MessageChain;
import org.hoshino.miraiclient4j.message.baseType.BaseType;
import org.hoshino.miraiclient4j.message.MessageEvent;
import org.hoshino.miraiclient4j.message.messageRequest.request.FriendMessage;
import org.hoshino.miraiclient4j.message.messageRequest.request.GroupMessage;
import org.hoshino.miraiclient4j.message.messageRequest.request.TempMessage;
import org.hoshino.miraiclient4j.utils.MessageUtil;

public class Bot implements Action{
    private Long qq;
    private HttpApiClient client;


    @Override
    public void send(MessageEvent event, BaseType... message) {
        String messageType = MessageUtil.getMessageType(event);
        Long id = MessageUtil.getId(event);
        MessageChain chain = new MessageChain();
        for (BaseType baseType : message) {
            chain.append(baseType);
        }
        if(messageType.equals("FriendMessage")){
            FriendMessage friendMessage = MessageBuilder.buildFriendMessage(id, chain);
            client.sendFriendMessage(friendMessage);
        }else if(messageType.equals("GroupMessage")){
            GroupMessage groupMessage = MessageBuilder.buildGroupMessage(id, chain);
            client.sendGroupMessage(groupMessage);
        }
    }

    @Override
    public void send(MessageEvent event, MessageChain chain) {
        String messageType = MessageUtil.getMessageType(event);
        Long id = MessageUtil.getId(event);
        if(messageType.equals("FriendMessage")){
            FriendMessage friendMessage = MessageBuilder.buildFriendMessage(id, chain);
            client.sendFriendMessage(friendMessage);
        }else if(messageType.equals("GroupMessage")){
            GroupMessage groupMessage = MessageBuilder.buildGroupMessage(id, chain);
            client.sendGroupMessage(groupMessage);
        }else if(messageType.equals("TempMessage")){
            TempMessage tempMessage = MessageBuilder.buildTempMessage(id, chain);
            client.sendTempMessage(tempMessage);
        }
    }


    //    @Override
//    public void sendToGroup(Message message) {
//
//    }

    public Bot() {
    }

    public Bot(Long qq) {
        this.qq = qq;
    }

    public Long getQq() {
        return qq;
    }

    public void setQq(Long qq) {
        this.qq = qq;
    }

    public HttpApiClient getClient() {
        return client;
    }

    public void setClient(HttpApiClient client) {
        this.client = client;
    }
}
