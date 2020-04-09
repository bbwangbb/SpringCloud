package cn.mb.springcloud.controller;

import cn.mb.springcloud.entities.CommonResult;
import cn.mb.springcloud.entities.Payment;
import cn.mb.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/hi")
    public String hi() {
        return "hi";
    }

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***********插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "新增成功！端口：" + port, result);
        }
        return new CommonResult<>(444, "新增失败！端口：" + port, null);
    }

    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("***********查出数据：" + payment);

        System.out.println("大家好哦！");

        if (payment != null) {
            return new CommonResult(200, "查询成功！端口：" + port, payment);
        }
        return new CommonResult<>(444, "查询失败！端口：" + port, null);
    }

    @GetMapping("/discovery")
    public Object discovery() {

        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("***********" + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            URI uri = instance.getUri();
            log.info("*************************" + uri);
        }

        return discoveryClient;
    }

    @GetMapping("/payment/getPort")
    public String getPort() {
        return port;
    }

}
