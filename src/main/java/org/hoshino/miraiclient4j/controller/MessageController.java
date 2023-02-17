package org.hoshino.miraiclient4j.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.hoshino.miraiclient4j.message.MessageEvent;
import org.hoshino.miraiclient4j.processor.MessageProcessor;
import org.hoshino.miraiclient4j.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private MessageProcessor messageProcessor;

    public MessageController(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    @PostMapping("/messageListener")
    public R<JSONObject> messageListener(@RequestBody String s){
        LOGGER.info("from mirai message:{}", s);
        MessageEvent receivedMessage = JSONUtil.toBean(s, MessageEvent.class);
        messageProcessor.add(receivedMessage);
        return R.ok();
    }

}
