package com.example.geekspringtest.mycglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/11/15 9:18 下午
 **/
public class MyCglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("cglib enhancer before target method ....");
        methodProxy.invokeSuper(o,objects);
        System.out.println("cglib enhancer after target ...");
        return null;
    }
}
