package org.hoshino.miraiclient4j.autoConfig;

import org.hoshino.miraiclient4j.adapter.HttpApiClient;
import org.hoshino.miraiclient4j.applicationListener.ApplicationShutdownListener;
import org.hoshino.miraiclient4j.applicationListener.ApplicationStartedListener;
import org.hoshino.miraiclient4j.aspect.annotation.EnableMiraiClient4j;
import org.hoshino.miraiclient4j.bot.Bot;
import org.hoshino.miraiclient4j.constant.MiraiURL;
import org.hoshino.miraiclient4j.context.ApplicationContextHolder;
import org.hoshino.miraiclient4j.context.MiraiContext;
import org.hoshino.miraiclient4j.factory.BotFactory;
import org.hoshino.miraiclient4j.processor.FriendMessageProcessor;
import org.hoshino.miraiclient4j.processor.GroupMessageProcessor;
import org.hoshino.miraiclient4j.processor.MessageProcessor;
import org.hoshino.miraiclient4j.properties.MiraiProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadPoolExecutor;

@AutoConfiguration
public class MiraiAutoConfiguration {

    @Configuration
    static public class AdapterConfiguration {
        @Bean
        public RestTemplate restTemplate(){
            return new RestTemplate();
        }

        @Bean
        @ConditionalOnProperty(prefix = "mirai.config", name = "api-adapter", havingValue = "http")
        public HttpApiClient httpApiClient(){
            return new HttpApiClient();
        }

    }

    @Configuration
    static public class ApplicationConfiguration {

        @Bean
        public ApplicationContextHolder applicationContextHolder(){
            return new ApplicationContextHolder();
        }

        @Bean
        public ApplicationStartedListener applicationStartedListener(MessageProcessor processor, ThreadPoolTaskExecutor executor, ApplicationContextHolder applicationContextHolder, MiraiContext context){
            return new ApplicationStartedListener(processor, executor, applicationContextHolder, context);
        }
        @Bean
        public ApplicationShutdownListener applicationShutdownListener(HttpApiClient httpApiClient, MiraiContext context){
            return new ApplicationShutdownListener(httpApiClient, context);
        }

    }

    @Configuration
    static public class CommandProcessorSupport {

        public CommandProcessorSupport() {

        }

        @Bean
        public FriendMessageProcessor friendMessageProcessor(ThreadPoolTaskExecutor executor){
            return new FriendMessageProcessor(executor);
        }

        @Bean
        public GroupMessageProcessor groupMessageProcessor(ThreadPoolTaskExecutor executor){
            return new GroupMessageProcessor(executor);
        }

        @Bean
        public MessageProcessor messageProcessor(
                @Qualifier("friendMessageProcessor") FriendMessageProcessor friendMessageProcessor,
                @Qualifier("groupMessageProcessor") GroupMessageProcessor groupMessageProcessor
        ){
            return new MessageProcessor(friendMessageProcessor, groupMessageProcessor);
        }

    }

    @Configuration
    @EnableConfigurationProperties(MiraiProperties.class)
    static public class MiraiContextConfiguration {
        private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

        @Bean
        public MiraiURL miraiURL(){
            return new MiraiURL();
        }

        @Bean
        @DependsOn({"applicationContextHolder"})
        public MiraiContext miraiContext(MiraiProperties properties){
            MiraiContext miraiContext = new MiraiContext();
            miraiContext.setVerifyKey(properties.getVerifyKey());
            miraiContext.setQq(properties.getQq());
            return miraiContext;
        }

    }

    @Configuration
    @EnableAsync
    @ConditionalOnMissingBean({ThreadPoolTaskExecutor.class})
    static public class ThreadPoolConfiguration {

        @Bean
        public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
            ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
            threadPoolTaskExecutor.setCorePoolSize(5);
            threadPoolTaskExecutor.setMaxPoolSize(10);
            threadPoolTaskExecutor.setQueueCapacity(2000);
            threadPoolTaskExecutor.setThreadNamePrefix("processor-->");
            threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
            threadPoolTaskExecutor.initialize();
            return threadPoolTaskExecutor;
        }

    }
}
