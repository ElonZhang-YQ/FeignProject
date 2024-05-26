package com.ez.springboot.orderservice.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Classname CustomAsapConfig
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/26
 */
@Component
public class CustomAsapConfig {

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
