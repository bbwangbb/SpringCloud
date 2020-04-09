package cn.mb.service.impl;

import cn.mb.dao.PaymentDao;
import cn.mb.entities.Payment;
import cn.mb.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public int save(Payment payment) {
        return paymentDao.save(payment);
    }
}
