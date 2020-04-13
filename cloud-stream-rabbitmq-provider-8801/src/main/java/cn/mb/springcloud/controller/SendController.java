package cn.mb.springcloud.controller;

import cn.mb.springcloud.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/send")
    public String send() {
        return messageService.send();
    }
}
