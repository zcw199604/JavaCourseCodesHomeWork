package com.example.geekspringtest.myproxy;

import java.lang.reflect.Proxy;


/**
 *
 * @author zcw
 * @date 2021/06/05 8:52 下午
 **/
public class TestProxy {

    public static void main(String[] args) {
        TargetService targetService = new TargetServiceImpl();
        MyJDKProxy jdkProxy = new MyJDKProxy(targetService);
        TargetService targetProxyService =
                (TargetService)Proxy.newProxyInstance(targetService.getClass().getClassLoader(),
                        targetService.getClass().getInterfaces(), jdkProxy);
        targetProxyService.tagetMethod();

    }
}
