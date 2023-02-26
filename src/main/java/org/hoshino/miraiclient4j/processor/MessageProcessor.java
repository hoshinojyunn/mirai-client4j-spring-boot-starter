package org.hoshino.miraiclient4j.processor;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.hoshino.miraiclient4j.exception.MessageChainEmptyException;
import org.hoshino.miraiclient4j.message.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;

public class MessageProcessor implements Runnable{
    private final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);
    private LinkedList<MessageEvent>messages;
    private FriendMessageProcessor friendMessageProcessor;
    private GroupMessageProcessor groupMessageProcessor;


    public MessageProcessor(FriendMessageProcessor friendMessageProcessor, GroupMessageProcessor groupMessageProcessor) {
        this.friendMessageProcessor = friendMessageProcessor;
        this.groupMessageProcessor = groupMessageProcessor;
        this.messages = new LinkedList<>();
    }


    // 如果webhook开放多个接口可能会产生锁竞争
    public synchronized void add(MessageEvent message){
        messages.addLast(message);
    }
    public void process(MessageEvent message) throws Exception {
        JSONArray messageChain = message.getMessageChain();
        if(messageChain==null||messageChain.isEmpty()||messageChain.size()<2)
            throw new MessageChainEmptyException();
        // 普通文字消息
        Object temp =  messageChain.get(1);
        String cmd = "",body = "";
        String text = "";

        text = JSONUtil.parseObj(temp).getStr("text");
        if(StringUtils.hasText(text)){
            String[]splits = text.split(" ");
            if(splits.length>=2) {
                StringBuffer sb = new StringBuffer();
                Arrays.stream(splits).skip(1).forEach(sb::append);
                body = sb.toString();
            }
            cmd = splits[0];
        }

        if(StringUtils.hasText(cmd) &&!cmd.startsWith("/")){
            LOGGER.info("非命令消息:{}", cmd + body);
            return;
        }
        String type = message.getType();
        LOGGER.info("收到命令消息:{},类型:{}", cmd + " " + body, type);
        switch (type){
            case "FriendMessage":
                friendMessageProcessor.process(message, cmd);
                break;
            case "GroupMessage":
                groupMessageProcessor.process(message, cmd);
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
                    LOGGER.warn("MessageProcessor was interrupted");
                }
                continue;
            }
            try {
                process(messages.pollFirst());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
