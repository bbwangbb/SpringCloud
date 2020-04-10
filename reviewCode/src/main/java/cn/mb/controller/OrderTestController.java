package cn.mb.controller;

import cn.mb.entities.OrderTest;
import cn.mb.service.OrderTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderTestController {

    @Autowired
    private OrderTestService orderTestService;

    @PostMapping("/orderTest/save")
    public int save(@RequestBody OrderTest orderTest) {
        return orderTestService.save(orderTest);
    }

}
