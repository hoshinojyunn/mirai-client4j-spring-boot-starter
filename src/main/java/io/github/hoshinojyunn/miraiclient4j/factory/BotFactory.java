package io.github.hoshinojyunn.miraiclient4j.factory;

import io.github.hoshinojyunn.miraiclient4j.adapter.HttpApiClient;
import io.github.hoshinojyunn.miraiclient4j.bot.Bot;
import io.github.hoshinojyunn.miraiclient4j.context.MiraiContext;

public class BotFactory {
    private MiraiContext miraiContext;
    private HttpApiClient httpClient;

    public BotFactory(MiraiContext miraiContext,HttpApiClient httpClient) {
        this.miraiContext = miraiContext;
        this.httpClient = httpClient;
    }

    public Bot newBot(){
        Bot bot = new Bot();
        bot.setQq(miraiContext.getQq());
        bot.setClient(httpClient);
        return bot;
    }

}
