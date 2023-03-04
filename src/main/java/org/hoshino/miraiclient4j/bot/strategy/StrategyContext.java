package org.hoshino.miraiclient4j.bot.strategy;

import cn.hutool.json.JSONObject;
import org.hoshino.miraiclient4j.adapter.HttpApiClient;
import org.hoshino.miraiclient4j.message.MessageBuilder;
import org.hoshino.miraiclient4j.message.MessageChain;
import org.hoshino.miraiclient4j.message.messageRequest.request.FriendMessage;
import org.hoshino.miraiclient4j.message.messageRequest.request.GroupMessage;
import org.hoshino.miraiclient4j.message.messageRequest.request.TempMessage;
import org.hoshino.miraiclient4j.utils.R;

public class StrategyContext {
    private SendStrategy strategy;
    private MessageChain chain;
    private HttpApiClient httpApiClient;
    private Long id;

    public StrategyContext(String type, MessageChain chain, HttpApiClient httpApiClient, Long id) {
        this.chain = chain;
        this.httpApiClient = httpApiClient;
        this.id = id;
        switch (type) {
            case "FriendMessage":
                strategy = new FriendStrategy(chain, httpApiClient, id);
                break;
            case "GroupMessage":
                strategy = new GroupStrategy(chain, httpApiClient, id);
                break;
            case "TempMessage":
                strategy = new TempStrategy(chain, httpApiClient, id);
                break;
        }
    }

    public R<JSONObject> doExecute(){
        return strategy.doStrategy();
    }
}
