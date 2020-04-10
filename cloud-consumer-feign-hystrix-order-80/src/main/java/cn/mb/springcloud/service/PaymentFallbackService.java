package cn.mb.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String normal(Integer id) {
        return "客户端的熔断， normal！";
    }

    @Override
    public String timeout(Integer id) {
        return "客户端的熔断， timeout！";
    }
}
