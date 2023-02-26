package org.hoshino.miraiclient4j.processor;

import java.lang.reflect.Method;

public class MessageTask implements Runnable{
    private Method method;
    private Object target;
    private Object[]args;

    public MessageTask(Method method, Object target, Object[] args) {
        this.method = method;
        this.target = target;
        this.args = args;
    }

    @Override
    public void run() {
        try {
            method.invoke(target, args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
