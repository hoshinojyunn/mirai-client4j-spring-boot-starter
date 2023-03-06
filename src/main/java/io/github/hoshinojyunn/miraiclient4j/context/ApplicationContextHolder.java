package io.github.hoshinojyunn.miraiclient4j.context;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Optional;


public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T>Optional<T>getBean(Class<T>clazz){
        return getBean(null, clazz);
    }

    public static <T>Optional<T>getBean(String name, Class<T>clazz){
        if(context == null)
            return Optional.empty();
        if(StrUtil.isBlank(name)||StrUtil.isEmpty(name))
            return Optional.of(context.getBean(clazz));
        return Optional.of(context.getBean(name, clazz));
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
