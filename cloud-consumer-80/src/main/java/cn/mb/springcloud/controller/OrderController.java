package cn.mb.springcloud.controller;

import cn.mb.springcloud.entities.CommonResult;
import cn.mb.springcloud.entities.Payment;
import cn.mb.springcloud.loadBalanced.LoadBalanced;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController {
    //  单机
//    private static String url = "http://localhost:8001";
    //  集群：但是需要开启RestTemplate的负载均衡功能，否则他不知道用哪个，在ApplicationContextConfig配置类中添加
    private static String url = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalanced loadBalanced;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment) {
        System.out.println("消费者调用创建订单");
        return restTemplate.postForObject(url + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        System.out.println("消费者调用查询订单");
        return restTemplate.getForObject(url + "/payment/getPaymentById/" + id, CommonResult.class);
    }

    //  entity
    @GetMapping("/consumer/payment/createForEntity")
    public CommonResult createForEntity(Payment payment) {
        System.out.println("消费者调用创建订单by entity");
        //  ...ForEntity返回的是包含有请求码等参数的变量
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(url + "/payment/create", payment, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }
        return new CommonResult(444, "操作失败！", null);
    }

    @GetMapping("/consumer/payment/getPaymentByIdForEntity/{id}")
    public CommonResult getPaymentByIdForEntity(@PathVariable("id") Long id) {
        System.out.println("消费者调用查询订单by entity");
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(url + "/payment/getPaymentById/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }
        return new CommonResult(444, "操作失败！", null);
    }

    @GetMapping("/order/getPort")
    public String getPort() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance instance = loadBalanced.getInstance(instances);
        if (instance == null) {
            return "没有对应的服务";
        }
        URI uri = instance.getUri();
        System.out.println(uri);
        return restTemplate.getForObject(uri.toString() + "/payment/getPort", String.class);
    }

}
