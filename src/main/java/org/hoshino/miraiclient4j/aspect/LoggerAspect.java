package org.hoshino.miraiclient4j.aspect;

import cn.hutool.json.JSONUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hoshino.miraiclient4j.aspect.annotation.OnCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
public class LoggerAspect {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(onCommand)")
    public Object logAdvice(ProceedingJoinPoint pjp, OnCommand onCommand){
        Object[] args = pjp.getArgs();
        Arrays.stream(args).forEach(arg -> LOGGER.info("传入参数:{}", JSONUtil.toJsonStr(arg)));
        Object res = null;
        try {
            res = pjp.proceed(args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return res;
    }
}
