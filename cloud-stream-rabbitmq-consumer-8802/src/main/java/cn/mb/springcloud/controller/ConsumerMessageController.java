package cn.mb.springcloud.controller;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class ConsumerMessageController {
    /**
     * 消费消息
     * @param message   在泛型中指定消息类型（可以是通用api的对象）
     */
    @StreamListener(Sink.INPUT)
    public void consumeMessage(Message<String> message) {
        System.out.println("收到的消息为：" + message.getPayload());
    }
}
