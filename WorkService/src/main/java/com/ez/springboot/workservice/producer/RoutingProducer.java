package com.ez.springboot.workservice.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname RoutingProducer
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/21
 */
@Component
public class RoutingProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     *
     * @param direct        交换机名
     * @param routingKey    交换机RoutingKey
     * @param message       消息
     */
    public void produceSingleMessage(String direct, String routingKey, String message) {

        rabbitTemplate.convertAndSend(direct, routingKey, message);
    }
}
