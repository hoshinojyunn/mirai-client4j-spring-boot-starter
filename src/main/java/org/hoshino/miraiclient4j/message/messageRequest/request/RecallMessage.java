package org.hoshino.miraiclient4j.message.messageRequest.request;

import org.hoshino.miraiclient4j.message.messageRequest.Message;
import org.hoshino.miraiclient4j.message.messageRequest.SessionKey;

public class RecallMessage extends SessionKey implements Message {
    private Long target;
    private Integer messageId;

    public RecallMessage() {
    }

    public RecallMessage(Long target, Integer messageId) {
        this.target = target;
        this.messageId = messageId;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}
