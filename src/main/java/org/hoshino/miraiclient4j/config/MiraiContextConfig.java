package org.hoshino.miraiclient4j.config;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.hoshino.miraiclient4j.bot.Bot;
import org.hoshino.miraiclient4j.constant.MiraiURL;
import org.hoshino.miraiclient4j.context.MiraiContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
@DependsOn("miraiURL")
public class MiraiContextConfig implements InitializingBean {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private RestTemplate restTemplate;
    private MiraiContext miraiContext;


    public void initSessionKey() throws JSONException {
        JSONObject params = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(mediaType);
        headers.set("Accept",MediaType.APPLICATION_JSON.toString());
        params.put("verifyKey", miraiContext.getVerifyKey());
        HttpEntity<String> entity = new HttpEntity<>(params.toString(), headers);
        // initialize: verify your qq account
        String res = restTemplate.postForEntity(MiraiURL.VERIFY_URL, entity, String.class).getBody();
        LOGGER.info("认证成功，sessionKey={}", res);
        // save session key
        miraiContext.setSession(JSONUtil.parseObj(res).getStr("session"));
    }

    public void activateSessionKey(){
        cn.hutool.json.JSONObject params = new cn.hutool.json.JSONObject();
        params.set("sessionKey", miraiContext.getSession());
        params.set("qq", miraiContext.getQq());
        String res = HttpUtil.post(MiraiURL.BIND_URL, JSONUtil.toJsonStr(params));
        LOGGER.info("绑定:{}", res);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initSessionKey();
        activateSessionKey();
    }

    public MiraiContextConfig(RestTemplate restTemplate, MiraiContext miraiContext) {
        this.restTemplate = restTemplate;
        this.miraiContext = miraiContext;
    }
}
