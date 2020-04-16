package cn.mb.alibaba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/test")
    public String test() {
        return restTemplate.getForObject("http://cloud-alibaba-sentinel-service/test", String.class);
    }

}
