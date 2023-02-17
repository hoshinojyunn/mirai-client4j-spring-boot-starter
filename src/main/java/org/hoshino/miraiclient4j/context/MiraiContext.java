package org.hoshino.miraiclient4j.context;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.hoshino.miraiclient4j.aspect.annotation.CommandListener;
import org.hoshino.miraiclient4j.aspect.annotation.OnCommand;
import org.hoshino.miraiclient4j.constant.MiraiURL;
import org.hoshino.miraiclient4j.properties.MiraiProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;

public class MiraiContext {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private String verifyKey;
    private String session;
    private Long qq;
    private Map<String, Method>processFunction;
    private Map<Method, Object>processBean;

    public MiraiContext() {
    }

    public MiraiContext(String verifyKey, String session, Long qq) throws JSONException {
        this.verifyKey = verifyKey;
        this.session = session;
        this.qq = qq;
    }



    public Optional<Method> findProcessFunction(String cmd){
        return Optional.ofNullable(processFunction.get(cmd));
    }
    public Optional<Object> findBeanWithMethod(Method method){
        return Optional.ofNullable(processBean.get(method));
    }

    public Long getQq() {
        return qq;
    }

    public void setQq(Long qq) {
        this.qq = qq;
    }

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Map<String, Method> getProcessFunction() {
        return processFunction;
    }

    public void setProcessFunction(Map<String, Method> processFunction) {
        this.processFunction = processFunction;
    }

    public Map<Method, Object> getProcessBean() {
        return processBean;
    }

    public void setProcessBean(Map<Method, Object> processBean) {
        this.processBean = processBean;
    }

}
