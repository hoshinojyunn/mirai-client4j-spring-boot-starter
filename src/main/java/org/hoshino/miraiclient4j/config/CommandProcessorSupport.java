package org.hoshino.miraiclient4j.config;


import org.hoshino.miraiclient4j.context.MiraiContext;
import org.hoshino.miraiclient4j.processor.FriendMessageProcessor;
import org.hoshino.miraiclient4j.processor.GroupMessageProcessor;
import org.hoshino.miraiclient4j.processor.MessageProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class CommandProcessorSupport {
    private MiraiContext context;

    public CommandProcessorSupport( MiraiContext context) {
        this.context = context;
    }

    @Bean
    public FriendMessageProcessor friendMessageProcessor(ThreadPoolTaskExecutor executor){
        return new FriendMessageProcessor(context, executor);
    }

    @Bean
    public GroupMessageProcessor groupMessageProcessor(ThreadPoolTaskExecutor executor){
        return new GroupMessageProcessor(context,executor);
    }

    @Bean
    public MessageProcessor messageProcessor(
            @Qualifier("friendMessageProcessor") FriendMessageProcessor friendMessageProcessor,
            @Qualifier("groupMessageProcessor") GroupMessageProcessor groupMessageProcessor
            ){
        return new MessageProcessor(friendMessageProcessor, groupMessageProcessor);
    }

}
