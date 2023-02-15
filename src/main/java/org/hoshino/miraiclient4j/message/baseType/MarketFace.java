package org.hoshino.miraiclient4j.message.baseType;

public class MarketFace extends AbstractType implements BaseType {
    private int id;
    private String name;

    public MarketFace(int id, String name) {
        super.setType("MarketFace");
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
