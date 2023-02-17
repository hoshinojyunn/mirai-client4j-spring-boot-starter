package org.hoshino.miraiclient4j.applicationListener;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.hoshino.miraiclient4j.aspect.annotation.CommandListener;
import org.hoshino.miraiclient4j.aspect.annotation.OnCommand;
import org.hoshino.miraiclient4j.constant.MiraiURL;
import org.hoshino.miraiclient4j.context.ApplicationContextHolder;
import org.hoshino.miraiclient4j.context.MiraiContext;
import org.hoshino.miraiclient4j.processor.MessageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private MessageProcessor processor;
    private ThreadPoolTaskExecutor executor;
    private MiraiContext context;

    public ApplicationStartedListener(MessageProcessor processor, ThreadPoolTaskExecutor executor, MiraiContext context) {
        this.processor = processor;
        this.executor = executor;
        this.context = context;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        try {
            initSessionKey();
        } catch (JSONException e) {
            LOGGER.info("session key initialize fail...");
        }
        try {
            activateSessionKey();
        } catch (JSONException e) {
            LOGGER.info("session key activate fail,check out your qq or network");
        }
        registry();
        executor.execute(processor);
        LOGGER.info("messageProcessor has been started!");
    }
    public void initSessionKey() throws JSONException {
        JSONObject params = new JSONObject();
        params.set("verifyKey", context.getVerifyKey());
        // initialize: verify your qq account
        String res = HttpUtil.post(MiraiURL.VERIFY, JSONUtil.toJsonStr(params));
        LOGGER.info("认证，sessionKey={}", res);
        // save session key
        context.setSession(JSONUtil.parseObj(res).getStr("session"));
    }

    public void activateSessionKey() throws JSONException {
        JSONObject params = new JSONObject();
        params.set("sessionKey", context.getSession());
        params.set("qq", context.getQq());
        String res = HttpUtil.post(MiraiURL.BIND, JSONUtil.toJsonStr(params));
        LOGGER.info("绑定:{}", res);
    }

    // 扫描容器中所有标记有CommandListener的类 将类中OnCommand标记的方法与其bean初始化到miraiContext
    public void registry(){
        ApplicationContext applicationContext = ApplicationContextHolder.getContext();
        Map<String, Object> targetBeans = applicationContext.getBeansWithAnnotation(CommandListener.class);
        List<Class<?>> allClasses = new ArrayList<>();
        // 拿到所有的带OnCommand的bean
        targetBeans.forEach((k,v)->allClasses.add(applicationContext.getType(k)));
        Map<String, Method>processMethods = new HashMap<>();
        Map<Method, Object>methodBeans = new HashMap<>();
        for(Class<?>clazz:allClasses){
            Method[] methods = clazz.getMethods();
            for(Method method:methods){
                OnCommand onCommand = null;
                // 找到带OnCommand注解的方法
                if(method!=null&&(onCommand = AnnotationUtils.findAnnotation(method, OnCommand.class))!=null){
                    processMethods.put(onCommand.command(), method);
                    methodBeans.put(method,applicationContext.getBean(method.getDeclaringClass()));
                }
            }
        }
        context.setProcessFunction(processMethods);
        context.setProcessBean(methodBeans);
    }
}