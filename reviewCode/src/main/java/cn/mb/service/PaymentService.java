package cn.mb.service;

import cn.mb.entities.Payment;

public interface PaymentService {

    Payment getPaymentById(Long id);

    int save(Payment payment);

}
