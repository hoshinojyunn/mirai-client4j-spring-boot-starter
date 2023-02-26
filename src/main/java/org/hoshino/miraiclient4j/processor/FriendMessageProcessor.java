package org.hoshino.miraiclient4j.processor;

import org.hoshino.miraiclient4j.context.MiraiContext;
import org.hoshino.miraiclient4j.exception.MessageTypeException;
import org.hoshino.miraiclient4j.message.MessageEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
