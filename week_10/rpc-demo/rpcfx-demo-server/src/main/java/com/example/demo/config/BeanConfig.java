package com.example.demo.config;

import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.OrderServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置接口的实现类
 *
 * @author zcw
 */
@Configuration
public class BeanConfig {

    @Bean("com.example.demo.service.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean("com.example.demo.service.OrderService")
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}
