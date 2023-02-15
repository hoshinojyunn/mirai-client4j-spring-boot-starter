package org.hoshino.miraiclient4j.processor;

import cn.hutool.json.JSONObject;
import org.hoshino.miraiclient4j.context.MiraiContext;
import org.hoshino.miraiclient4j.exception.MessageTypeException;
import org.hoshino.miraiclient4j.message.messageChainType.MessageEvent;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service
public class FriendMessageProcessor implements Processor{
    private MiraiContext context;

    public FriendMessageProcessor(MiraiContext context) {
        this.context = context;
    }

    @Override
    public void process(MessageEvent message, String cmd) throws MessageTypeException, InvocationTargetException, IllegalAccessException {
        if(!message.getType().equals("FriendMessage"))
            throw new MessageTypeException("FriendMessageProcessor can‘t process this message type");
        JSONObject sender = message.getSender();
        Method processFunction = context.findProcessFunction(cmd).orElse(null);
        Assert.notNull(processFunction, "can't find process function of the command");
        Object target = context.findBeanWithMethod(processFunction).orElse(null);
        Assert.notNull(target, "can't find the bean of the method,maybe it isn't inject to spring context");
        processFunction.invoke(target, message);

    }
}
