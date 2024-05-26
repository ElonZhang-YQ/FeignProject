package com.ez.springboot.workservice.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname PublishAndSubscribeProducer
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/21
 */
@Component
public class PublishAndSubscribeProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * Publish/Subscribe模型，生产者发送对消息
     * @param exchangeName  交换机名
     * @param queueName     队列名，如果为""，表示队列随机名称，用来创建临时队列
     * @param message       消息内容
     */
    public void produceSingleMessage(String exchangeName, String queueName, String message) {

        rabbitTemplate.convertAndSend(exchangeName, queueName, message);
    }
}
