package io.github.hoshinojyunn.miraiclient4j.message.baseType;

public class File extends AbstractType implements BaseType {
    private String id;
    private String name;
    private Long size;

    public File(String id, String name, Long size) {
        super.setType("File");
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

}
