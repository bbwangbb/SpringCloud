package cn.mb.springcloud.controller;

import cn.mb.springcloud.entities.CommonResult;
import cn.mb.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/openfeign/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/consumer/opentFeignTimeOutTest")
    public String opentFeignTimeOutTest() {
        return paymentService.opentFeignTimeOutTest();
    }

}
