package io.github.hoshinojyunn.miraiclient4j.config;

import io.github.hoshinojyunn.miraiclient4j.adapter.HttpApiClient;
import io.github.hoshinojyunn.miraiclient4j.bot.Bot;
import io.github.hoshinojyunn.miraiclient4j.context.MiraiContext;
import io.github.hoshinojyunn.miraiclient4j.factory.BotFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotInitConfiguration {

    @Bean
    public BotFactory botFactory(MiraiContext context, HttpApiClient client){
        return new BotFactory(context, client);
    }

    @Bean
    public Bot bot(BotFactory factory){
        return factory.newBot();
    }



}
