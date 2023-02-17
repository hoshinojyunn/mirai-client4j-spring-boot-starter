package org.hoshino.miraiclient4j.config;

import org.hoshino.miraiclient4j.bot.Bot;
import org.hoshino.miraiclient4j.factory.BotFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(BotFactory.class)
public class BotInitConfiguration {
    private BotFactory factory;

    @Bean
    public Bot bot(){
        return factory.newBot();
    }

    public BotInitConfiguration(BotFactory factory) {
        this.factory = factory;
    }
}
