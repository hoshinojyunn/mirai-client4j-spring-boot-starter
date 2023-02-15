package org.hoshino.miraiclient4j.message.baseType;

public class Json extends AbstractType implements BaseType {
    private Json String;

    public Json(Json string) {
        super.setType("Json");
        String = string;
    }

    public Json getString() {
        return String;
    }

    public void setString(Json string) {
        String = string;
    }


}
