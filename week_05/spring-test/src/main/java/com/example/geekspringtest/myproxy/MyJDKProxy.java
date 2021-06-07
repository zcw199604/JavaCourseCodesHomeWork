package com.example.geekspringtest.myproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 *
 * @author zcw
 * @date 2021/06/05 8:41 下午
 **/
public class MyJDKProxy implements InvocationHandler {

    private Object targetObject;

    public MyJDKProxy(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before target method ...");
        Object object = method.invoke(targetObject, args);
        System.out.println("after target method ...");
        return object;
    }
}
