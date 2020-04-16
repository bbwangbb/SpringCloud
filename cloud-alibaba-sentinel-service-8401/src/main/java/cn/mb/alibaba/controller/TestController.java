package cn.mb.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "test";
    }


    @GetMapping("/testOverThread")
    public String testOverThread() {
        log.info(Thread.currentThread().getName() + "\ttestOverThread");
        return "testOverThread";
    }
}
