package cn.mb.springcloud.controller;

import cn.mb.springcloud.entities.CommonResult;
import cn.mb.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderConsulController {

    private static String url = "http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/consul/payment")
    public String paymentInfo() {
        String result = restTemplate.getForObject(url + "/payment/consul", String.class);
        return result;
    }
}
