package com.example.demo.service.impl;

import com.example.demo.annotation.ProxyService;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

/**
 * @author zcw
 */
@ProxyService(service = "com.example.demo.service.UserService",tags = "v2",group = "user")
public class UserServiceImpl implements UserService {

    @Override
    public User findById(Integer id) {
        return new User(id, "RPC");
    }
}
