package io.github.hoshinojyunn.miraiclient4j.message.baseType;

public class Poke extends AbstractType implements BaseType {
    private String name;

    public Poke(String name) {
        super.setType("Poke");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
