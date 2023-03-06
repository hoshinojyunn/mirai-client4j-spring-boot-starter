package io.github.hoshinojyunn.miraiclient4j.message.baseType;

public class MiraiCode extends AbstractType implements BaseType {
    private String code;

    public MiraiCode(String code) {
        super.setType("MiraiCode");
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
