package org.hoshino.miraiclient4j.message.messageRequest;

import org.hoshino.miraiclient4j.message.MessageChain;

public class FriendMessage extends SessionKey implements Message{
    // 好友qq号
    private Long target;
    private MessageChain messageChain;

    public FriendMessage(Long target, MessageChain messageChain) {
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
