package cn.mb.springcloud.service.impl;

import cn.mb.springcloud.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

//  表示为生产消息
@EnableBinding(Source.class)
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        UUID message = UUID.randomUUID();
        output.send(MessageBuilder.withPayload(message).build());
        System.out.println("发送消息：" + message);
        return message.toString();
    }
}
