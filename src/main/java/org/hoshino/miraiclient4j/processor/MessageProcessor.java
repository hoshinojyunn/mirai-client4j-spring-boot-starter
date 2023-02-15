package org.hoshino.miraiclient4j.processor;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.hoshino.miraiclient4j.exception.MessageChainEmptyException;
import org.hoshino.miraiclient4j.exception.MessageTypeException;
import org.hoshino.miraiclient4j.message.baseType.BaseType;
import org.hoshino.miraiclient4j.message.messageChainType.MessageEvent;
import org.hoshino.miraiclient4j.utils.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

@Service
public class MessageProcessor implements Runnable{
    private final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);
    private LinkedList<MessageEvent>messages;
    private FriendMessageProcessor friendMessageProcessor;
    private ThreadPoolTaskExecutor executor;

    public MessageProcessor(FriendMessageProcessor friendMessageProcessor,ThreadPoolTaskExecutor executor) {
        this.friendMessageProcessor = friendMessageProcessor;
        this.executor = executor;
        this.messages = new LinkedList<>();
        executor.execute(this);
    }


    // 如果webhook开放多个接口可能会产生锁竞争
    public synchronized void add(MessageEvent message){
        messages.addLast(message);
    }

    private void process(MessageEvent message) throws MessageChainEmptyException, MessageTypeException, InvocationTargetException, IllegalAccessException {
        JSONArray messageChain = message.getMessageChain();
        if(messageChain==null||messageChain.isEmpty()||messageChain.size()<2)
            throw new MessageChainEmptyException();
        // 普通文字消息
        Object temp =  messageChain.get(1);
        String[]text = JSONUtil.parseObj(temp).getStr("text").split(" ");
        if(!text[0].startsWith("/")){
            LOGGER.info("非命令消息:{}", text[0] + " " + text[1]);
            return;
        }
        LOGGER.info("收到命令消息:{}", text[0] + " " + text[1]);
        String type = message.getType();
        switch (type){
            case "FriendMessage":
                friendMessageProcessor.process(message, text[0]);
                break;
            default:
                break;
        }
    }


    @Override
    public void run() {
        while(true){
            if(messages.isEmpty()){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            try {
                process(messages.pollFirst());
            } catch (MessageChainEmptyException | MessageTypeException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
