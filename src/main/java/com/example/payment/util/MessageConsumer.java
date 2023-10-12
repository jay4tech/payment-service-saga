package com.example.payment.util;


import com.example.payment.entity.Payment;
import com.example.payment.model.Order;
import com.example.payment.service.IPaymentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @Autowired
    IPaymentService paymentService;

    @RabbitListener(queues = {"${queue.name.payment}"})
    public void receive(@Payload String message) {
        System.out.println("Message " + message);
        Order orderDetails = UtilityMapper.responseToModel(message);
        paymentService.workOnMessage(orderDetails);
    }

}