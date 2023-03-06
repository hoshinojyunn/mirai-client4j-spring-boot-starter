package io.github.hoshinojyunn.miraiclient4j.config;

import io.github.hoshinojyunn.miraiclient4j.adapter.HttpApiClient;
import io.github.hoshinojyunn.miraiclient4j.context.ApplicationContextHolder;
import io.github.hoshinojyunn.miraiclient4j.applicationListener.ApplicationShutdownListener;
import io.github.hoshinojyunn.miraiclient4j.applicationListener.ApplicationStartedListener;
import io.github.hoshinojyunn.miraiclient4j.context.MiraiContext;
import io.github.hoshinojyunn.miraiclient4j.processor.MessageProcessor;
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