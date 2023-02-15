package org.hoshino.miraiclient4j.constant;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MiraiURL{
    private static String apiAdapter;
    private static String url;

    public static String BASE_URL;
    public static String VERIFY_URL;
    public static String BIND_URL;
    public static String SESSION_INFO_URL;
    public static String SESSION_RELEASE_URL;
    public static String COUNT_MESSAGE_URL;
    public static String FRIEND_LIST_URL;
    public static String SEND_FRIEND_MESSAGE_URL;

    @PostConstruct
    public void init(){
        BASE_URL = apiAdapter + "://" + url;
        VERIFY_URL = BASE_URL + "/verify";
        BIND_URL = BASE_URL + "/bind";
        SESSION_INFO_URL = BASE_URL + "/sessionInfo";
        SESSION_RELEASE_URL = BASE_URL + "/release";
        COUNT_MESSAGE_URL = BASE_URL + "/countMessage";
        FRIEND_LIST_URL = BASE_URL + "/friendList";
        SEND_FRIEND_MESSAGE_URL = BASE_URL + "/sendFriendMessage";
    }

    @Value("${mirai.config.api-adapter}")
    public void setApiAdapter(String apiAdapter) {
        MiraiURL.apiAdapter = apiAdapter;
    }
    @Value("${mirai.config.url}")
    public void setUrl(String url) {
        MiraiURL.url = url;
    }
}
