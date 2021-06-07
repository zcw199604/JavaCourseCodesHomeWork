package com.example.geekspringtest.mycache;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * @author zcw
 * @date 2021/06/05 3:41 下午
 **/
@Component
@Aspect
@Slf4j
public class MyCacheAspect {

    @Around("execution(* com.example.geekspringtest.test.*.*(..))")
    public void doAround(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Class<?>[] argTypes = new Class[joinPoint.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        MyCache myCache = method.getAnnotation(MyCache.class);
        if (null == myCache)
            return;
        log.info("open cache:{},cacheTime:{}", myCache.open(), myCache.seconds());
        if (myCache.open()) {
            log.info("添加缓存处理。。。todo ");
        }

    }

    private void doBefore() {
        log.info("doBefore...todo ");

    }

    private void doAfter() {
        log.info("doAfter...todo ");
    }
}
