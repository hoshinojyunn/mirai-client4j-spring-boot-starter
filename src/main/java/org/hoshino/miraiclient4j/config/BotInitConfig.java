package org.hoshino.miraiclient4j.config;

import org.hoshino.miraiclient4j.bot.Bot;
import org.hoshino.miraiclient4j.factory.BotFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Configuration
@ConditionalOnBean(BotFactory.class)
public class BotInitConfig {
    private BotFactory factory;

    @Bean
    public Bot bot(){
        return factory.newBot();
    }

    public BotInitConfig(BotFactory factory) {
        this.factory = factory;
    }
}
