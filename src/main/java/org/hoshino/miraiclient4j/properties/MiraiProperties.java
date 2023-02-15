package org.hoshino.miraiclient4j.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mirai.config")
public class MiraiProperties {
    private String verifyKey;
    private String url;
    private Long qq;
    private String apiAdapter;

    public MiraiProperties() {
        this.verifyKey = null;
        this.url = "localhost:8081";
        this.qq = null;
        this.apiAdapter = "http";
    }

    public String getApiAdapter() {
        return apiAdapter;
    }

    public void setApiAdapter(String apiAdapter) {
        this.apiAdapter = apiAdapter;
    }

    public Long getQq() {
        return qq;
    }

    public void setQq(Long qq) {
        this.qq = qq;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey;
    }
}
