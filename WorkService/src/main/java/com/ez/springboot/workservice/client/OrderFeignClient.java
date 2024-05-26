package com.ez.springboot.workservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname OrderFeignClient
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/19
 */
//@Component
//@FeignClient(name="OrderService", url="http://localhost:8080")
public interface OrderFeignClient {

    @RequestMapping("/order/getOrder")
    String getOrder();
}
