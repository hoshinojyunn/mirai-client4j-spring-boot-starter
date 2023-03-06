package io.github.hoshinojyunn.miraiclient4j.message.messageRequest;

public abstract class SessionKey {
    private String sessionKey;
    public String getSessionKey(){
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
