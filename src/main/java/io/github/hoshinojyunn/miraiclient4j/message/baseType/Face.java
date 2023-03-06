package io.github.hoshinojyunn.miraiclient4j.message.baseType;

public class Face extends AbstractType implements BaseType {
    private int faceId;
    private String name;

    public Face(int faceId, String name) {
        super.setType("Face");
        this.faceId = faceId;
        this.name = name;
    }

    public int getFaceId() {
        return faceId;
    }

    public void setFaceId(int faceId) {
        this.faceId = faceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
