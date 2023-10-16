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
        Payment payment = new Payment();
        payment.setOrderId(orderDetails.getId());
        payment.setStatus(PaymentStatus.INITIATED);
        createOrUpdate(payment);
        messageSender.sendNotification(UtilityMapper.getJsonString(orderDetails));
    }

    @Override
    public Payment receivePayment(Payment payment) {
        Payment paymentDb = paymentRepository.save(payment);
        if(paymentDb.getStatus().equals(PaymentStatus.SUCCESS)){
            messageSender.sendOrder(UtilityMapper.getJsonString(paymentDb));
        }
        return paymentDb;
    }
}
