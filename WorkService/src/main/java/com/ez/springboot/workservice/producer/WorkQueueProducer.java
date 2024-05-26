package com.ez.springboot.workservice.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname WorkQueueProducer
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/21
 */
@Component
public class WorkQueueProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送单条消息
     * @param queueName 虽然指定了队列名，但是生产者不能创建队列的，只有在消费者存在，并对该队列进行监听时，如果该队列不存在，才会创建。
     * @param message
     */
    public void produceSingleMessage(String queueName, String message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

}
