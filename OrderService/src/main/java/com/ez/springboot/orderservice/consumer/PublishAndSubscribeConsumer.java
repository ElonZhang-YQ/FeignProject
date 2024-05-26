package com.ez.springboot.orderservice.consumer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Classname PublishAndSubscribeConsumer
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/21
 */
@Component
public class PublishAndSubscribeConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    // 临时创建一个Queue
                    value = @Queue(value = "PublishAndSubscribeQueue"),
                    // 临时绑定交换机
                    exchange = @Exchange(value = "fanout", type = "fanout")
            )
    })
    public void consume01(String message) {
        System.out.println("consumer01 :" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    // 临时创建一个Queue
                    value = @Queue(value = "PublishAndSubscribeQueue"),
                    // 临时绑定交换机
                    exchange = @Exchange(value = "fanout", type = "fanout")
            )
    })
    public void consume02(String message) {
        System.out.println("consumer02 :" + message);
    }
}
