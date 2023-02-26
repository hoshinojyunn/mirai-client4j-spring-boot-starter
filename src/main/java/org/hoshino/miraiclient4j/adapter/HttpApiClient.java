package org.hoshino.miraiclient4j.adapter;


import cn.hutool.core.lang.hash.Hash;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.hoshino.miraiclient4j.adapter.api.HttpApi;
import org.hoshino.miraiclient4j.constant.MiraiURL;
import org.hoshino.miraiclient4j.context.ApplicationContextHolder;
import org.hoshino.miraiclient4j.context.MiraiContext;
import org.hoshino.miraiclient4j.message.messageRequest.FriendMessage;
import org.hoshino.miraiclient4j.message.messageRequest.GroupMessage;
import org.hoshino.miraiclient4j.message.messageRequest.TempMessage;
import org.hoshino.miraiclient4j.utils.R;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class HttpApiClient extends HttpApi {

    private static class HttpApiUtil {
        private static MiraiContext context;
        private static RestTemplate restTemplate;
        static{
            context = ApplicationContextHolder.getBean(MiraiContext.class).orElse(null);
            restTemplate = ApplicationContextHolder.getBean(RestTemplate.class).orElse(null);
        }

        public static R<JSONObject> get(String url){
            HashMap<String, Object> params = new HashMap<>();
            params.put("sessionKey", context.getSession());
            return get(url, params);
        }
        public static R<JSONObject> get(String url, Map<String,Object>params){
            params.put("sessionKey", context.getSession());
            return R.ok(JSONUtil.parseObj(HttpUtil.get(url, params)));
        }
        public static R<JSONObject> upload(String url, String key, File file) throws JSONException {
            org.springframework.boot.configurationprocessor.json.JSONObject params = new org.springframework.boot.configurationprocessor.json.JSONObject();
            HttpHeaders headers = new HttpHeaders();
            MediaType mediaType = MediaType.parseMediaType("multipart/form-data;charset=UTF-8");
            headers.setContentType(mediaType);
            headers.set("Accept",MediaType.APPLICATION_JSON.toString());
            FileSystemResource resource = new FileSystemResource(file);
            params.put("sessionKey", context.getSession());
            params.put(key, resource);

            HttpEntity<String> entity = new HttpEntity<>(params.toString(), headers);
            // initialize: verify your qq account
            String res = restTemplate.postForEntity(url, entity, String.class).getBody();
            return R.ok(JSONUtil.parseObj(res));
        }

        public static R<JSONObject> post(String url, String requestBody){
            JSONObject params = JSONUtil.parseObj(requestBody);
            params.set("sessionKey", context.getSession());
            requestBody = JSONUtil.toJsonStr(params);
            return R.ok(JSONUtil.parseObj(HttpUtil.post(url, requestBody)));
        }
        public static R<JSONObject> post(String url, Map<String,Object>params){
            params.put("sessionKey", context.getSession());
            return R.ok(JSONUtil.parseObj(HttpUtil.post(url, params)));
        }
        public static R<JSONObject> post(String url,Map<String,Object>params, String contentType){
            String res = HttpUtil.createPost(url).contentType(contentType).form(params).execute().body();
            return R.ok(JSONUtil.parseObj(res));
        }
    }
    @Override
    public R<JSONObject> getSessionInfo() {
        return HttpApiUtil.get(MiraiURL.SESSION_INFO);
    }

    @Override
    public R<JSONObject> releaseSession(Long qq) {
        JSONObject params = new JSONObject();
        params.set("qq", qq);
        MiraiContext context = ApplicationContextHolder.getBean(MiraiContext.class).orElse(null);
        Assert.notNull(context, "release session require the miraiContext must not be null!");
        R<JSONObject> resp = HttpApiUtil.post(MiraiURL.SESSION_RELEASE, JSONUtil.toJsonStr(params));
        context.setSession(null);
        return resp;
    }

    @Override
    public R<JSONObject> countMessage() {
        return HttpApiUtil.get(MiraiURL.COUNT_MESSAGE);
    }

    @Override
    public R<JSONObject> fetchMessage(int count) {
        return null;
    }

    @Override
    public R<JSONObject> fetchLatestMessage(int count) {
        return null;
    }

    @Override
    public R<JSONObject> peekMessage(int count) {
        return null;
    }

    @Override
    public R<JSONObject> peekLatestMessage(int count) {
        return null;
    }

    @Override
    public R<JSONObject> uploadImage(String type, File img) {
        return null;
    }

    @Override
    public R<JSONObject> uploadVoice(String type, File voice) {
        return null;
    }

    @Override
    public R<JSONObject> fileUpload(String type, Long target, String path, File file) {
        return null;
    }

    @Override
    public R<JSONObject> about() {
        return HttpApiUtil.get(MiraiURL.ABOUT);
    }

    @Override
    public R<JSONObject> botList() {
        return HttpApiUtil.get(MiraiURL.BOT_LIST);
    }

    @Override
    public R<JSONObject> messageFromId(int messageId, Long target) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("messageId", messageId);
        params.put("target", target);
        return HttpApiUtil.get(MiraiURL.MESSAGE_FROM_ID, params);
    }

    @Override
    public R<JSONObject> friendList() {
        return HttpApiUtil.get(MiraiURL.FRIEND_LIST);
    }

    @Override
    public R<JSONObject> groupList() {
        return HttpApiUtil.get(MiraiURL.GROUP_LIST);
    }

    @Override
    public R<JSONObject> memberList(Long target) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("target", target);
        return HttpApiUtil.get(MiraiURL.MEMBER_LIST, params);
    }

    @Override
    public R<JSONObject> botProfile() {
        return HttpApiUtil.get(MiraiURL.BOT_PROFILE);
    }

    @Override
    public R<JSONObject> friendProfile(Long target) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("target",target);
        return HttpApiUtil.get(MiraiURL.FRIEND_PROFILE, params);
    }

    @Override
    public R<JSONObject> memberProfile(Long target, Long memberId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("target", target);
        params.put("memberId", memberId);
        return HttpApiUtil.get(MiraiURL.MEMBER_PROFILE, params);
    }

    @Override
    public R<JSONObject> userProfile(Long target) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("target", target);
        return HttpApiUtil.get(MiraiURL.USER_PROFILE, params);
    }

    @Override
    public R<JSONObject> sendFriendMessage(FriendMessage message) {
        return HttpApiUtil.post(MiraiURL.SEND_FRIEND_MESSAGE, JSONUtil.toJsonStr(message));
    }

    @Override
    public R<JSONObject> sendGroupMessage(GroupMessage message) {
        return HttpApiUtil.post(MiraiURL.SEND_GROUP_MESSAGE, JSONUtil.toJsonStr(message));
    }

    @Override
    public R<JSONObject> sendTempMessage(TempMessage message) {
        return HttpApiUtil.post(MiraiURL.SEND_TEMP_MESSAGE, JSONUtil.toJsonStr(message));
    }
}
