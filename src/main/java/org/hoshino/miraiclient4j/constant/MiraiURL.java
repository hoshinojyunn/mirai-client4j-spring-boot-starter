package org.hoshino.miraiclient4j.constant;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class MiraiURL{
    private static String apiAdapter;
    private static String url;

    public static String BASE;
    public static String VERIFY;
    public static String BIND;
    public static String SESSION_INFO;
    public static String SESSION_RELEASE;
    public static String COUNT_MESSAGE;
    public static String FRIEND_LIST;
    public static String SEND_FRIEND_MESSAGE;
    public static String SEND_GROUP_MESSAGE;
    public static String SEND_TEMP_MESSAGE;
    public static String SEND_NUDGE;
    public static String RECALL;
    public static String ROAMING_MESSAGES;
    public static String FILE_LIST;
    public static String FILE_INFO;
    public static String FILE_MKDIR;
    public static String FILE_DELETE;
    public static String FILE_MOVE;
    public static String FILE_RENAME;
    public static String UPLOAD_IMAGE;
    public static String UPLOAD_VOICE;
    public static String FILE_UPLOAD;

    @PostConstruct
    public void init(){
        BASE = apiAdapter + "://" + url;
        VERIFY = BASE + "/verify";
        BIND = BASE + "/bind";
        SESSION_INFO = BASE + "/sessionInfo";
        SESSION_RELEASE = BASE + "/release";
        COUNT_MESSAGE = BASE + "/countMessage";
        FRIEND_LIST = BASE + "/friendList";
        SEND_FRIEND_MESSAGE = BASE + "/sendFriendMessage";
        SEND_GROUP_MESSAGE = BASE + "/sendGroupMessage";
        SEND_TEMP_MESSAGE = BASE + "/sendTempMessage";
        SEND_NUDGE = BASE + "/sendNudge";
        RECALL = BASE + "/recall";
        ROAMING_MESSAGES = BASE + "/roamingMessages";
        FILE_LIST = BASE + "/file/list";
        FILE_INFO = BASE + "/file/info";
        FILE_MKDIR = BASE + "/file/mkdir";
        FILE_DELETE = BASE + "/file/delete";
        FILE_MOVE = BASE + "/file/move";
        FILE_RENAME = BASE + "/file/rename";
        UPLOAD_IMAGE = BASE + "/uploadImage";
        UPLOAD_VOICE = BASE + "/uploadVoice";
        FILE_UPLOAD = BASE + "/file/upload";
    }

    @Value("${mirai.config.api-adapter}")
    public void setApiAdapter(String apiAdapter) {
        MiraiURL.apiAdapter = apiAdapter;
    }
    @Value("${mirai.config.mirai-url}")
    public void setUrl(String url) {
        MiraiURL.url = url;
    }
}
