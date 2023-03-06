package io.github.hoshinojyunn.miraiclient4j.message.baseType;

public class Dice extends AbstractType implements BaseType {
    private int value;

    public Dice(int value) {
        super.setType("Dice");
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
