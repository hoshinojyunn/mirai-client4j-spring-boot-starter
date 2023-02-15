package org.hoshino.miraiclient4j.context;

import org.hoshino.miraiclient4j.aspect.OnCommandRegister;
import org.hoshino.miraiclient4j.properties.MiraiProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

@EnableConfigurationProperties({MiraiProperties.class})
@Component
public class MiraiContext{
    private final MiraiProperties properties;
    private String verifyKey;
    private String session;
    private Long qq;
    private Map<String, Method>processFunction;
    private Map<Method, Object>processBean;

    public MiraiContext(MiraiProperties properties) {
        this.properties = properties;
        verifyKey = this.properties.getVerifyKey();
        qq = this.properties.getQq();
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
