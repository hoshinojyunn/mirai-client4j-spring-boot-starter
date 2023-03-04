package org.hoshino.miraiclient4j.config;

import org.hoshino.miraiclient4j.adapter.HttpApiClient;
import org.hoshino.miraiclient4j.applicationListener.ApplicationShutdownListener;
import org.hoshino.miraiclient4j.applicationListener.ApplicationStartedListener;
import org.hoshino.miraiclient4j.context.ApplicationContextHolder;
import org.hoshino.miraiclient4j.context.MiraiContext;
import org.hoshino.miraiclient4j.processor.MessageProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ApplicationStartedListener applicationStartedListener(MessageProcessor processor, ThreadPoolTaskExecutor executor, ApplicationContextHolder applicationContextHolder, MiraiContext context){
        return new ApplicationStartedListener(processor, executor, applicationContextHolder, context);
    }

    @Bean
    public ApplicationShutdownListener applicationShutdownListener(HttpApiClient httpApiClient, MiraiContext context){
        return new ApplicationShutdownListener(httpApiClient, context);
    }

}