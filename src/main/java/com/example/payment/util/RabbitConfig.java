package com.example.payment.util;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {


    @Value("${queue.name.order}")
    private String queueOrder;

    @Bean
    public Queue queueOrder() {
        return new Queue(queueOrder, true);
    }

    @Value("${queue.name.payment}")
    private String queuePayment;

    @Bean
    public Queue queuePayment() {
        return new Queue(queuePayment, true);
    }
}