package com.ez.springboot.orderservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname OrderController
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/19
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/getOrder")
    public String getOrder() {
        return "Hello OpenFeign";
    }
}
