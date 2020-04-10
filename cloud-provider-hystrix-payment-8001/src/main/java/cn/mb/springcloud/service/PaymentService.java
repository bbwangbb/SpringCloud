package cn.mb.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {

    //  正常访问
    public String normal() {
        return "normal";
    }

    //  访问超时
    @HystrixCommand(fallbackMethod = "timeoutFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public String timeout() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "timeout 3s" ;
    }

    public String timeoutFallback() {
        return "超时，请重新尝试！";
    }

    /** 服务熔断      ------------------------*/
    @HystrixCommand(fallbackMethod = "paymentCircutBreaker_fallback",commandProperties = {
        //  在10s内调用失败超过6次，就发生熔断
        @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  //打开断路器
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),   //一定时间内（ms）
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")    //失败率
    })
    public String paymentCircutBreaker(Integer id) {
        if (id < 0 ) {
            throw  new RuntimeException();
        }
        return "success";
    }

    public String paymentCircutBreaker_fallback(Integer id) {
        return "当前id：" + id + "\t发生熔断";
    }


}
