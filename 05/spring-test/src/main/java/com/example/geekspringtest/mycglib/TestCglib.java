package com.example.geekspringtest.mycglib;

import org.springframework.cglib.proxy.Enhancer;


/**
 *
 * @author zcw
 * @date 2021/06/05 9:26 下午
 **/
public class TestCglib {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CGLIBTargetService.class);
        enhancer.setCallback(new MyCglibProxy());
        CGLIBTargetService cglibProxy = (CGLIBTargetService)enhancer.create();
        cglibProxy.tagetMethod();
    }
}
