package io.github.hoshinojyunn.miraiclient4j.message.baseType;

public class Xml extends AbstractType implements BaseType {
    private String xml;

    public Xml(String xml) {
        super.setType("Xml");
        this.xml = xml;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

}
