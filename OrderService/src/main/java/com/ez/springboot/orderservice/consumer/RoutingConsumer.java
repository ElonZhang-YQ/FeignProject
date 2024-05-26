package com.ez.springboot.orderservice.consumer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Classname RoutingConsumer
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/21
 */
@Component
public class RoutingConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    // 创建临时队列，并且监听。消息发送完毕之后，队列会被删除
                    value = @Queue,
                    // 临时队列绑定交换机（name和value一样）
                    exchange = @Exchange(name = "topic", type = "topic"),
                    // 临时队列的RoutingKey 可以设置多个 可以使用通配符
                    key = {"rabbit.#", "rabbit.*"}
            )
    })
    public void consume01(String message) {
        System.out.println("consume01: " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "topic", type = "topic"),
                    key = {"rabbit.*"}
            )
    })
    public void consume02(String message) {
        System.out.println("consume02: " + message);
    }
}
