package com.example.demo.service.impl;

import com.example.demo.annotation.ProxyService;
import com.example.demo.exception.CustomException;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;

/**
 * @author zcw
 */
@ProxyService(service = "com.example.demo.service.OrderService",tags = "v2",group = "order")
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findById(Integer id) {
        return new Order(1, "RPC", 1);
    }

    @Override
    public Order findError() {
        throw new CustomException("Custom exception");
    }
}
