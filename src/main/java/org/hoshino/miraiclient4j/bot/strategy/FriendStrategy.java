package org.hoshino.miraiclient4j.bot.strategy;

import cn.hutool.json.JSONObject;
import org.hoshino.miraiclient4j.adapter.HttpApiClient;
import org.hoshino.miraiclient4j.message.MessageBuilder;
import org.hoshino.miraiclient4j.message.MessageChain;
import org.hoshino.miraiclient4j.message.messageRequest.request.FriendMessage;
import org.hoshino.miraiclient4j.utils.R;

public class FriendStrategy implements SendStrategy{
    private MessageChain chain;
    private HttpApiClient httpApiClient;
    private Long id;

    public FriendStrategy(MessageChain chain, HttpApiClient httpApiClient, Long id) {
        this.chain = chain;
        this.httpApiClient = httpApiClient;
        this.id = id;
    }

    @Override
    public R<JSONObject> doStrategy() {
        FriendMessage friendMessage = MessageBuilder.buildFriendMessage(id, chain);
        return httpApiClient.sendFriendMessage(friendMessage);
    }
}
