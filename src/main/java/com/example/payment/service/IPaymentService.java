package com.example.payment.service;

import com.example.payment.entity.Payment;
import com.example.payment.model.Order;

public interface IPaymentService {
    Payment getPaymentByOrderId(Long orderId);
    Payment createOrUpdate(Payment payment);
    Payment getPayment(Long id);

    void workOnMessage(Order orderDetails);

    Payment receivePayment(Payment payment);
}
