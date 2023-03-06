package io.github.hoshinojyunn.miraiclient4j.message.baseType;

public class Quote extends AbstractType implements BaseType {
    private int id;
    private Long groupId;
    private Long senderId;
    private Long targetId;
    private Object origin;

    public Quote() {
    }

    public Quote(int id, Long groupId, Long senderId, Long targetId, Object origin) {
        super.setType("Quote");
        this.id = id;
        this.groupId = groupId;
        this.senderId = senderId;
        this.targetId = targetId;
        this.origin = origin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Object getOrigin() {
        return origin;
    }

    public void setOrigin(Object origin) {
        this.origin = origin;
    }

}
