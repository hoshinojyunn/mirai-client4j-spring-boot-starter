package io.github.hoshinojyunn.miraiclient4j.processor;

import io.github.hoshinojyunn.miraiclient4j.message.MessageEvent;
import io.github.hoshinojyunn.miraiclient4j.exception.MessageTypeException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.InvocationTargetException;

public class FriendMessageProcessor implements Processor{
    private ThreadPoolTaskExecutor executor;

    public FriendMessageProcessor(ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void process(MessageEvent message, String cmd) throws MessageTypeException, InvocationTargetException, IllegalAccessException {
        if(!message.getType().equals("FriendMessage"))
            throw new MessageTypeException("FriendMessageProcessor can‘t process this message type");
        MessageTask task = MessageTaskBuilder.build(message, cmd);
        executor.execute(task);
    }
}
