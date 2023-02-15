package org.hoshino.miraiclient4j.message.baseType;

public class Voice extends AbstractType implements BaseType {
    private String voiceId;
    private String url;
    private String path;
    private String base64;
    private Long length;

    public Voice(String voiceId, String url, String path, String base64, Long length) {
        super.setType("Voice");
        this.voiceId = voiceId;
        this.url = url;
        this.path = path;
        this.base64 = base64;
        this.length = length;
    }

    public String getVoiceId() {
        return voiceId;
    }

    public void setVoiceId(String voiceId) {
        this.voiceId = voiceId;
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

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
}
