package com.example.payment.util;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    @Qualifier("queueNotification")
    private Queue queueNotification;


    @Autowired
    @Qualifier("queueOrder")
    private Queue queueOrder;

    public void sendNotification(String message) {
        rabbitTemplate.convertAndSend(queueNotification.getName(), message);
    }


    public void sendOrder(String message) {
        rabbitTemplate.convertAndSend(queueOrder.getName(), message);
    }
}
