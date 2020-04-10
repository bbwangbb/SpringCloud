package cn.mb.springcloud.controller;

import cn.mb.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/normal/{id}")
    public String normal(@PathVariable("id") Integer id) {
        String result = paymentService.normal();
        log.info("-----------result：" + result + "\tid：" + id);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String timeout(@PathVariable("id") Integer id) {
        String result = paymentService.timeout();
        log.info("-----------result：" + result + "\tid：" + id);
        return result;
    }

    @GetMapping("/payment/hystrix/paymentCircutBreaker/{id}")
    public String paymentCircutBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircutBreaker(id);
        log.info("-----------result：" + result + "\tid：" + id);
        return result;
    }

}
