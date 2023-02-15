package org.hoshino.miraiclient4j.aspect;

import cn.hutool.core.annotation.AnnotationUtil;
import org.hoshino.miraiclient4j.aspect.annotation.CommandListener;
import org.hoshino.miraiclient4j.aspect.annotation.OnCommand;
import org.hoshino.miraiclient4j.context.ApplicationContextHolder;
import org.hoshino.miraiclient4j.context.MiraiContext;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.*;

@Component
@DependsOn({"miraiContext","applicationContextHolder"})
public class OnCommandRegister {
    private MiraiContext context;

    public OnCommandRegister(MiraiContext context) {
        Assert.notNull(context, "In OnCommandRegister,mirai context can not be null");
        this.context = context;
        registry();
    }

    // 扫描容器中所有标记有CommandListener的类 将类中OnCommand标记的方法与其bean初始化到miraiContext
    public void registry(){
        ApplicationContext applicationContext = ApplicationContextHolder.getContext();
        Map<String, Object> targetBeans = applicationContext.getBeansWithAnnotation(CommandListener.class);
        List<Class<?>>allClasses = new ArrayList<>();
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
