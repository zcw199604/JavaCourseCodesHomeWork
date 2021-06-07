package com.example.geekspringtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author zcw
 * @date 2021/06/05 10:08 下午
 **/
@Configuration
public class MyAutoConfigBean {

    @Bean
    public MyBean getMyBean() {
        MyBean myBean = new MyBean();
        myBean.setAge(18);
        myBean.setName("xptest");
        return myBean;
    }


}
