package org.hoshino.miraiclient4j.processor;


import org.hoshino.miraiclient4j.aspect.annotation.OnCommand;
import org.hoshino.miraiclient4j.context.ApplicationContextHolder;
import org.hoshino.miraiclient4j.context.MiraiContext;
import org.hoshino.miraiclient4j.message.MessageEvent;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

public class MessageTaskBuilder {
    private static MiraiContext context;
    static {
        context = ApplicationContextHolder.getBean(MiraiContext.class).orElse(null);
    }

    public static MessageTask build(MessageEvent event, String cmd){
        Method processFunction = context.findProcessFunction(cmd).orElse(null);
        Assert.notNull(processFunction, "can't find process function of the command");
        Object target = context.findBeanWithMethod(processFunction).orElse(null);
        Assert.notNull(target, "can't find the bean of the method,maybe it isn't inject to spring context");
        Object[]args = new Object[]{event};
        return new MessageTask(processFunction, target,args);
    }

}
