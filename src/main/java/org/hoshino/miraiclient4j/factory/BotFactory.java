package org.hoshino.miraiclient4j.factory;

import org.hoshino.miraiclient4j.adapter.HttpApiClient;
import org.hoshino.miraiclient4j.bot.Bot;
import org.hoshino.miraiclient4j.context.MiraiContext;
import org.springframework.stereotype.Component;

@Component
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
