package com.ez.springboot.workservice.controller;

import com.ez.springboot.workservice.producer.PublishAndSubscribeProducer;
import com.ez.springboot.workservice.producer.RoutingProducer;
import com.ez.springboot.workservice.producer.WorkQueueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname MessageController
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/21
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private WorkQueueProducer workQueueProducer;
    @Autowired
    private PublishAndSubscribeProducer publishAndSubscribeProducer;
    @Autowired
    private RoutingProducer routingProducer;

    @RequestMapping("/workQueueSingle")
    public void sendWorkQueueSingleMessage(String queueName, String message) {

        workQueueProducer.produceSingleMessage(queueName, message);
    }

    @RequestMapping("/publishScribeQueueSingle")
    public void sendPublishAndSubscribeMessage(String exchangeName, String queueName, String message) {

        publishAndSubscribeProducer.produceSingleMessage(exchangeName, queueName, message);
    }

    @RequestMapping("/routingQueueSingle")
    public void sendRoutingMessage(String exchangeName, String routingKey, String message) {

        routingProducer.produceSingleMessage(exchangeName, routingKey, message);
    }
}
