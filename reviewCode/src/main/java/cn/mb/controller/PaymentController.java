package cn.mb.controller;

import cn.mb.entities.MyResult;
import cn.mb.entities.Payment;
import cn.mb.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/save")
    public MyResult save(@RequestBody Payment payment) {
        int result = paymentService.save(payment);
        log.info("**********************插入结果：" + result);

        if (result > 0) {
            return new MyResult(200, "插入成功！", result);
        }
        return new MyResult(444, "插入失败！");
    }

    @GetMapping("/getPaymentById/{id}")
    public MyResult getPaymentById(Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("**********************查询结果：" + payment);

        if (payment != null) {
            return new MyResult(200, "查询成功！", payment);
        }
        return new MyResult(444, "查询失败！");
    }

}
