package com.zcw.selfstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //将自动装配类移到other目录模拟是否装配成功
        ConfigurableApplicationContext context =  SpringApplication.run(Application.class, args);
        School school = (School) context.getBean(School.class);
        System.out.println(school);
    }
}
