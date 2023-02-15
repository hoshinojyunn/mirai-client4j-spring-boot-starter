package org.hoshino.miraiclient4j.bot;


import org.hoshino.miraiclient4j.adapter.HttpApiClient;
import org.hoshino.miraiclient4j.message.MessageBuilder;
import org.hoshino.miraiclient4j.message.MessageChain;
import org.hoshino.miraiclient4j.message.messageRequest.FriendMessage;
import org.hoshino.miraiclient4j.message.messageRequest.Message;

public class Bot implements Action{
    private Long qq;
    private HttpApiClient client;

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

    @Override
    public void sendToFriend(FriendMessage message) {
//        MessageBuilder.buildFriendMessage(target, new MessageChain().app);
        client.sendFriendMessage(message);
    }

    @Override
    public void send(Message message) {

    }

//    @Override
//    public void sendToGroup(Message message) {
//
//    }
}
