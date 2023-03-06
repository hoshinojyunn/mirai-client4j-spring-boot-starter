package io.github.hoshinojyunn.miraiclient4j.message.messageRequest.request;


import io.github.hoshinojyunn.miraiclient4j.message.messageRequest.Message;
import io.github.hoshinojyunn.miraiclient4j.message.messageRequest.SessionKey;

public class NudgeMessage extends SessionKey implements Message {
    private Long target;
    private Long subject;
    /**
     * 可以是 Group Friend Stranger
     */
    private String kind;

    public NudgeMessage() {
    }

    public NudgeMessage(Long target, Long subject, String kind) {
        this.target = target;
        this.subject = subject;
        this.kind = kind;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public Long getSubject() {
        return subject;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
