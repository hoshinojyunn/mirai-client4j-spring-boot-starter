package org.hoshino.miraiclient4j.message.messageRequest.request;

import org.hoshino.miraiclient4j.message.MessageChain;
import org.hoshino.miraiclient4j.message.messageRequest.Message;
import org.hoshino.miraiclient4j.message.messageRequest.SessionKey;

public class CommonMessage extends SessionKey implements Message {
    private Long target;
    private MessageChain messageChain;

    public CommonMessage() {
    }

    public CommonMessage(Long target, MessageChain messageChain) {
        this.target = target;
        this.messageChain = messageChain;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public MessageChain getMessageChain() {
        return messageChain;
    }

    public void setMessageChain(MessageChain messageChain) {
        this.messageChain = messageChain;
    }
}
