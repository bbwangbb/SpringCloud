package cn.mb.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope   //支持Nacos的动态刷新
public class ConfigClientController {

    @Value("${info}")
    private String info;

    @GetMapping("/config/getInfo")
    public String getInfo() {
        return info;
    }

}
