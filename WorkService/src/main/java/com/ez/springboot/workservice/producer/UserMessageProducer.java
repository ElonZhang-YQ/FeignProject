package com.ez.springboot.workservice.producer;

import com.ez.springboot.workservice.bean.User;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Classname UserMessageProducer
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/26
 */
@Component
public class UserMessageProducer {

    private static final String LOGIN_EXCHANGE_NAME = "UserSignIn";
    private static final String UPDATE_EXCHANGE_NAME = "UserUpdate";
    private static final String SIGNUP_EXCHANGE_NAME = "UserSignUp";
    private static final String LOGIN_QUEUE_NAME = "UserLoginMessageQueue";
    private static final String UPDATE_QUEUE_NAME = "UserUpdateMessageQueue";
    private static final String SIGNUP_QUEUE_NAME = "UserSignUpMessageQueue";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void setUp() {
        /**
         * 声明Rabbit的解码方式，不声明的话，会使用默认的SimpleMessageConverter来进行解码，但是无法解码复杂对象
         * 显式设置解码对象为Jackson2JsonMessageConverter
         */
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    /**
     * 发送登录成功信息
     * @param message
     */
    public void produceUserLoginMessage(String message) {

        rabbitTemplate.convertAndSend(LOGIN_EXCHANGE_NAME, LOGIN_QUEUE_NAME, message);
    }

    /**
     * 发送更新成功信息
     * @param message
     */
    public void produceUserUpdateMessage(String message) {

        rabbitTemplate.convertAndSend(UPDATE_EXCHANGE_NAME, UPDATE_QUEUE_NAME, message);
    }

    /**
     * 发送注册成功信息
     * @param user
     */
    public void produceUserSignUpMessage(User user) {

        rabbitTemplate.convertAndSend(SIGNUP_EXCHANGE_NAME, SIGNUP_QUEUE_NAME, user);
    }
}
