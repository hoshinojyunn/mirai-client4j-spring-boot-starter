package org.hoshino.miraiclient4j.message.baseType;

public class Source extends AbstractType implements BaseType {
    private int id;
    // 时间戳
    private int time;

    public Source() {
    }

    public Source(int id, int time){
        super.setType("Source");
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


}
