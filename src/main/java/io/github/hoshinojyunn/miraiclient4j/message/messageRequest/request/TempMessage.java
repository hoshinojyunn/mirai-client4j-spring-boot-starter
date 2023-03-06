package io.github.hoshinojyunn.miraiclient4j.message.messageRequest.request;

import io.github.hoshinojyunn.miraiclient4j.message.MessageChain;
import io.github.hoshinojyunn.miraiclient4j.message.messageRequest.Message;
import io.github.hoshinojyunn.miraiclient4j.message.messageRequest.SessionKey;

public class TempMessage extends SessionKey implements Message {
    private Long qq;
    private Long group;
    private MessageChain messageChain;

    public TempMessage() {
    }

    public TempMessage(Long qq, Long group, MessageChain messageChain) {
        this.qq = qq;
        this.group = group;
        this.messageChain = messageChain;
    }

    public Long getQq() {
        return qq;
    }

    public void setQq(Long qq) {
        this.qq = qq;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public MessageChain getMessageChain() {
        return messageChain;
    }

    public void setMessageChain(MessageChain messageChain) {
        this.messageChain = messageChain;
    }
}
