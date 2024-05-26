package com.ez.springboot.workservice.controller;

import com.ez.springboot.workservice.client.OrderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname OrderFeignController
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/19
 */
@RestController
@RequestMapping("/feign")
public class OrderFeignController {

//    @Autowired
//    private OrderFeignClient orderFeignClient;
//
//    @RequestMapping("/getOrder")
//    public String getOrder(){
//        return orderFeignClient.getOrder();
//    }
}
