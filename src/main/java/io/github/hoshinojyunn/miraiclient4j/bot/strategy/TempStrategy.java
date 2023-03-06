package io.github.hoshinojyunn.miraiclient4j.bot.strategy;

import cn.hutool.json.JSONObject;
import io.github.hoshinojyunn.miraiclient4j.adapter.HttpApiClient;
import io.github.hoshinojyunn.miraiclient4j.message.MessageBuilder;
import io.github.hoshinojyunn.miraiclient4j.message.MessageChain;
import io.github.hoshinojyunn.miraiclient4j.message.messageRequest.request.TempMessage;
import io.github.hoshinojyunn.miraiclient4j.utils.R;

public class TempStrategy implements SendStrategy{
    private MessageChain chain;
    private HttpApiClient httpApiClient;
    private Long id;

    public TempStrategy(MessageChain chain, HttpApiClient httpApiClient, Long id) {
        this.chain = chain;
        this.httpApiClient = httpApiClient;
        this.id = id;
    }

    @Override
    public R<JSONObject> doStrategy() {
        TempMessage tempMessage = MessageBuilder.buildTempMessage(id, chain);
        return httpApiClient.sendTempMessage(tempMessage);
    }
}
