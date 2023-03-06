package io.github.hoshinojyunn.miraiclient4j.processor;

import io.github.hoshinojyunn.miraiclient4j.message.MessageEvent;
import io.github.hoshinojyunn.miraiclient4j.exception.MessageTypeException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class GroupMessageProcessor implements Processor{

    private ThreadPoolTaskExecutor executor;

    public GroupMessageProcessor(ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void process(MessageEvent message, String cmd) throws Exception {
        if(!message.getType().equals("GroupMessage"))
            throw new MessageTypeException("GroupMessageProcessor can‘t process this message type");
        MessageTask task = MessageTaskBuilder.build(message, cmd);
        executor.execute(task);
    }
}
