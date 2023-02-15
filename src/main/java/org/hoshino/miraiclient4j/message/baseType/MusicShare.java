package org.hoshino.miraiclient4j.message.baseType;

public class MusicShare extends AbstractType implements BaseType {
    private String kind;
    private String title;
    private String summary;
    private String jumpUrl;
    private String pictureUrl;
    private String musicUrl;
    private String brief;

    public MusicShare(String kind, String title, String summary, String jumpUrl, String pictureUrl, String musicUrl, String brief) {
        super.setType("MusicShare");
        this.kind = kind;
        this.title = title;
        this.summary = summary;
        this.jumpUrl = jumpUrl;
        this.pictureUrl = pictureUrl;
        this.musicUrl = musicUrl;
        this.brief = brief;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

}
