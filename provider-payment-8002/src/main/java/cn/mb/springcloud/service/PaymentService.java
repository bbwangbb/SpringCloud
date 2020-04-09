package cn.mb.springcloud.service;

import cn.mb.springcloud.entities.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);

}
