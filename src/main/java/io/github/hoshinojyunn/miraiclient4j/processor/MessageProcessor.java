package io.github.hoshinojyunn.miraiclient4j.processor;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import io.github.hoshinojyunn.miraiclient4j.message.MessageEvent;
import io.github.hoshinojyunn.miraiclient4j.exception.MessageChainEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MessageProcessor implements Runnable{
    private final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);
    private LinkedBlockingQueue<MessageEvent> messages;
    private FriendMessageProcessor friendMessageProcessor;
    private GroupMessageProcessor groupMessageProcessor;


    public MessageProcessor(FriendMessageProcessor friendMessageProcessor, GroupMessageProcessor groupMessageProcessor) {
        this.friendMessageProcessor = friendMessageProcessor;
        this.groupMessageProcessor = groupMessageProcessor;
        this.messages = new LinkedBlockingQueue<>(1024 * 1024);
    }


    // 如果webhook开放多个接口可能会产生锁竞争
    public void add(MessageEvent message){
        messages.offer(message);
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
            // 拼接命令后的内容
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
            try {
                MessageEvent event = messages.take();
                Assert.notNull(event, "消息事件不能为null");
                process(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
