package com.example.payment.model;



import com.example.payment.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;
    private Long productId;
    private Long userId;
    private PaymentStatus status;
}
