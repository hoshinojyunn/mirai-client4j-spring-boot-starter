package org.hoshino.miraiclient4j.processor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TaskProxy implements InvocationHandler,Runnable {
    private Method method;
    private Object target;
    private Object[]args;

    public TaskProxy(Method method, Object target, Object[]args) {
        this.method = method;
        this.target = target;
        this.args = args;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxy, args);
    }

    @Override
    public void run() {
        try {
            invoke(target, method, args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
