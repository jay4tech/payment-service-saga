package com.example.payment.service;

import com.example.payment.entity.Payment;
import com.example.payment.entity.PaymentStatus;
import com.example.payment.model.Order;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.util.MessageSender;
import com.example.payment.util.UtilityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    MessageSender messageSender;

    @Override
    public Payment getPaymentByOrderId(Long orderId) {
        return paymentRepository.getPaymentByOrderId(orderId);
    }

    @Override
    public Payment createOrUpdate(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public void workOnMessage(Order orderDetails) {
        if (orderDetails != null) {
            Payment payment = getPaymentByOrderId(orderDetails.getId());
            if (payment != null) {
                payment.setStatus(orderDetails.getStatus());
                createOrUpdate(payment);
            } else {
                orderDetails.setStatus(PaymentStatus.FAILED);
                messageSender.send(UtilityMapper.getJsonString(orderDetails));
            }
        } else {
            orderDetails.setStatus(PaymentStatus.FAILED);
            messageSender.send(UtilityMapper.getJsonString(orderDetails));
        }
    }
}
