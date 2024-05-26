package com.ez.springboot.orderservice.consumer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Classname Consumer
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/21
 */
@Component
//@RabbitListener(queues = "SingleMessageQueue")
//@RabbitListener(queuesToDeclare = @Queue(value = "SingleMessageQueue"))
public class WorkQueueConsumer {

    /**
     * RabbitListener表示该类 / 方法是一个消费者，同时指定该消费者监听的队列，如果该队列不存在，则创建队列。
     * 同时还可以设置队列的特性durable，autoDelete，exclusive等，true表示开启，false表示关闭。在默认情况下创建的队列，durable为true，autoDelete和exclusive都为false。
     */

    /**
     *
     * @param message 消息内容
     */
    @RabbitListener(queuesToDeclare = @Queue(value = "SingleMessageQueue"))
    public void consume(String message) {
        System.out.println("Consume1 :" + message);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "SingleMessageQueue"))
    public void consume2(String message) {
        System.out.println("Consume2 :" + message);
    }
}
