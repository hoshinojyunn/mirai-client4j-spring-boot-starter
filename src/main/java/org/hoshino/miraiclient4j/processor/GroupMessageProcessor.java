package org.hoshino.miraiclient4j.processor;

import org.hoshino.miraiclient4j.context.MiraiContext;
import org.hoshino.miraiclient4j.exception.MessageTypeException;
import org.hoshino.miraiclient4j.message.MessageEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

public class GroupMessageProcessor implements Processor{
    private MiraiContext context;
    private ThreadPoolTaskExecutor executor;

    public GroupMessageProcessor(MiraiContext context, ThreadPoolTaskExecutor executor) {
        this.context = context;
        this.executor = executor;
    }

    @Override
    public void process(MessageEvent message, String cmd) throws Exception {
        if(!message.getType().equals("GroupMessage"))
            throw new MessageTypeException("GroupMessageProcessor can‘t process this message type");
        Method processFunction = context.findProcessFunction(cmd).orElse(null);
        Assert.notNull(processFunction, "can't find process function of the command");
        Object target = context.findBeanWithMethod(processFunction).orElse(null);
        Assert.notNull(target, "can't find the bean of the method,maybe it isn't inject to spring context");
        Object[]args = new Object[]{message};
        executor.execute(new TaskProxy(processFunction, target, args));
    }
}
