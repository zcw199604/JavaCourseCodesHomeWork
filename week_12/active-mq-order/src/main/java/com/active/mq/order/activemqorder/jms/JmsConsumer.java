/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.active.mq.order.activemqorder.jms;

import com.active.mq.order.activemqorder.model.Order;
import com.google.gson.Gson;
import org.apache.activemq.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author zcw
 */
@Component
public class JmsConsumer {

    private Gson gson = new Gson();

    @JmsListener(destination = "${activeTopic}")
    public void receiveMessage(final Message message) throws JMSException {
        System.out.println(message.toString());
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)message;
            String messageData = textMessage.getText();
            System.out.println(messageData);
            Order order = gson.fromJson(messageData, Order.class);
            System.out.println(order.getId());
        }
    }

}
