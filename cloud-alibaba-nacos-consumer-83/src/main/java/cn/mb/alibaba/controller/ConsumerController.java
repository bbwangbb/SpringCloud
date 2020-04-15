package cn.mb.alibaba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Value("${provider.url}")
    private String providerUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/getProviderPort/{id}")
    public String getProviderPort(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(providerUrl + "/getPort/" + id, String.class);
    }

}
