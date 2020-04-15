package cn.mb.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/getPort/{id}")
    public String getPort(@PathVariable("id") Integer id) {
        return "端口：" + port + "，id：" + id;
    }

}
