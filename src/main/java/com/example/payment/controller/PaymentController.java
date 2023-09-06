package com.example.payment.controller;

import com.example.payment.entity.Payment;
import com.example.payment.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "payment")
public class PaymentController {

    @Autowired
    IPaymentService orderService;

    @GetMapping("/order/{orderId}")
    public Payment getPaymentByOrderId(@PathVariable Long orderId) {
        return orderService.getPaymentByOrderId(orderId);
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return orderService.getPayment(id);
    }

    @PostMapping("/")
    public Payment getPayment(@RequestBody Payment payment) {
        return orderService.createOrUpdate(payment);
    }
}