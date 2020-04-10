package cn.mb.springcloud.controller;

import cn.mb.springcloud.service.PaymentHystrixService;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalFallback")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/hystrix/normal/{id}")
    public String normal(@PathVariable("id") Integer id) {
        return paymentHystrixService.normal(id);
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
    @HystrixCommand
//            (fallbackMethod = "timeoutFallback", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
//    })
    public String timeout(@PathVariable("id") Integer id) {
        return paymentHystrixService.timeout(id);
    }

    public String timeoutFallback(@PathVariable("id") Integer id) {
        return "80超时，请重新尝试！";
    }

    public String globalFallback() {
        return "全局熔断！";
    }

}
