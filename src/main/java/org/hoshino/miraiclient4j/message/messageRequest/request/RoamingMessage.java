package org.hoshino.miraiclient4j.message.messageRequest.request;

public class RoamingMessage {
    private Long timeStart;
    private Long timeEnd;
    private Long target;

    public RoamingMessage() {
    }

    public RoamingMessage(Long timeStart, Long timeEnd, Long target) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.target = target;
    }

    public Long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Long timeStart) {
        this.timeStart = timeStart;
    }

    public Long getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Long timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }
}
