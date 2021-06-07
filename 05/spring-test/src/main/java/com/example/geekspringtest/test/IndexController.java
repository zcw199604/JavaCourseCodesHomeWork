package com.example.geekspringtest.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.geekspringtest.config.MyBean;
import com.example.geekspringtest.mycache.MyCache;

import lombok.extern.slf4j.Slf4j;


/**
 *
 * @author zcw
 * @date 2021/06/05 8:22 下午
 **/
@RestController
@RequestMapping(value = "/index")
@Slf4j
public class IndexController {

    @Autowired
    private MyBean myBean;

    @RequestMapping("/test")
    @MyCache(seconds = "5")
    public String testIndex(){
        String name = myBean.getName();
        Integer age = myBean.getAge();
        log.info("age:{},name{}",age,name);
        return "indextest";
    }

    @RequestMapping("/test1")
    @MyCache(open = false, seconds = "15")
    public String testNoCache(){
        String name = myBean.getName();
        Integer age = myBean.getAge();
        log.info("age:{},name{}",age,name);
        return "cachetest";
    }

}
