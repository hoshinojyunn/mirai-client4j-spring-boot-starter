package io.github.hoshinojyunn.miraiclient4j.message.baseType;

public class App extends AbstractType implements BaseType {
    private String content;

    public App(String content) {
        super.setType("App");
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
