package org.hoshino.miraiclient4j.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("mirai.config")
public class MiraiProperties {
    private String verifyKey = null;
    private String miraiUrl = "localhost:8081";
    private Long qq = null;
    private String apiAdapter = "http";


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

    public String getMiraiUrl() {
        return miraiUrl;
    }

    public void setMiraiUrl(String miraiUrl) {
        this.miraiUrl = miraiUrl;
    }

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey;
    }
}
