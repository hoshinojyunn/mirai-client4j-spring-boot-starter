package org.hoshino.miraiclient4j.message.baseType;

public class Plain extends AbstractType implements BaseType {
    private String text;

    public Plain(String text) {
        super.setType("Plain");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
