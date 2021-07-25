package com.active.mq.order.activemqorder;

import com.active.mq.order.activemqorder.jms.JmsProducer;
import com.active.mq.order.activemqorder.model.Order;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zcw
 */
@SpringBootApplication
@Slf4j
public class ActivemqOrderApplication implements ApplicationRunner {

    @Value("${activeTopic}")
    private String topic;

    @Autowired
    private JmsProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(ActivemqOrderApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Gson gson = new Gson();
        for (int i = 0; i < 10; i++) {
            Order order = new Order(System.currentTimeMillis());
            producer.sendMessage(topic, gson.toJson(order));
            log.info("send message to topic " + topic + " :: " + gson.toJson(order));
            Thread.sleep(500);
        }
    }
}
