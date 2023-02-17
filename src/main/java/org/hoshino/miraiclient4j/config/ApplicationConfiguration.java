package org.hoshino.miraiclient4j.config;

import org.hoshino.miraiclient4j.adapter.HttpApiClient;
import org.hoshino.miraiclient4j.applicationListener.ApplicationShutdownListener;
import org.hoshino.miraiclient4j.applicationListener.ApplicationStartedListener;
import org.hoshino.miraiclient4j.bot.Bot;
import org.hoshino.miraiclient4j.context.ApplicationContextHolder;
import org.hoshino.miraiclient4j.context.MiraiContext;
import org.hoshino.miraiclient4j.processor.MessageProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ApplicationConfiguration {


    @Bean
    public ApplicationShutdownListener applicationShutdownListener(HttpApiClient httpApiClient, Bot bot){
        return new ApplicationShutdownListener(httpApiClient, bot);
    }

    @Bean
    public ApplicationStartedListener applicationStartedListener(MessageProcessor processor, ThreadPoolTaskExecutor executor, MiraiContext context){
        return new ApplicationStartedListener(processor, executor, context);
    }

    @Bean
    public ApplicationContextHolder applicationContextHolder(){
        return new ApplicationContextHolder();
    }
}
