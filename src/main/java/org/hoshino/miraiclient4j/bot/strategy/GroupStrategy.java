package org.hoshino.miraiclient4j.bot.strategy;

import cn.hutool.json.JSONObject;
import org.hoshino.miraiclient4j.adapter.HttpApiClient;
import org.hoshino.miraiclient4j.message.MessageBuilder;
import org.hoshino.miraiclient4j.message.MessageChain;
import org.hoshino.miraiclient4j.message.messageRequest.request.GroupMessage;
import org.hoshino.miraiclient4j.utils.R;

public class GroupStrategy implements SendStrategy{
    private MessageChain chain;
    private HttpApiClient httpApiClient;
    private Long id;

    public GroupStrategy(MessageChain chain, HttpApiClient httpApiClient, Long id) {
        this.chain = chain;
        this.httpApiClient = httpApiClient;
        this.id = id;
    }

    @Override
    public R<JSONObject> doStrategy() {
        GroupMessage groupMessage = MessageBuilder.buildGroupMessage(id, chain);
        return httpApiClient.sendGroupMessage(groupMessage);
    }
}
