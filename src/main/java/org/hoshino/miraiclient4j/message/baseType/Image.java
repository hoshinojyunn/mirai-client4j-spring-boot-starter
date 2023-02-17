package org.hoshino.miraiclient4j.message.baseType;

public class Image extends AbstractType implements BaseType {
    private String imageId;
    private String url;
    private String path;
    private String base64;

    public Image(String url) {
        super.setType("Image");
        this.url = url;
    }

    public Image(String imageId, String url, String path, String base64) {
        super.setType("Image");
        this.imageId = imageId;
        this.url = url;
        this.path = path;
        this.base64 = base64;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

}
