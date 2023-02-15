package org.hoshino.miraiclient4j.message.baseType;

public class At extends AbstractType implements BaseType {
    private Long target;
    private String display;

    public At(Long target, String display) {
        super.setType("At");
        this.target = target;
        this.display = display;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

}
