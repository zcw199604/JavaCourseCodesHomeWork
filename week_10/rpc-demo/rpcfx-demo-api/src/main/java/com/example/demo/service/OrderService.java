package com.example.demo.service;

import com.example.demo.model.Order;

/**
 * @author zcw
 */
public interface OrderService {

    /**
     * find by id
     * @param id id
     * @return order
     */
    Order findById(Integer id);

    /**
     * return exception
     * @return exception
     */
    Order findError();
}
