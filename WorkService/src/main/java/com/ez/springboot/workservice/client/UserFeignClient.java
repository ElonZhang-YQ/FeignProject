package com.ez.springboot.workservice.client;

import com.ez.springboot.workservice.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Classname UserFeignClient
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/26
 */
@Component
@FeignClient(name = "OrderService", url = "http://localhost:8080")
public interface UserFeignClient {

    @RequestMapping("/user/findAllUser")
    List<User> findUser();

    @RequestMapping("/user/findSingle")
    User findUser(@RequestParam("username") String username);

    @RequestMapping("/user/signIn")
    boolean signIn(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping("/user/signUp")
    User signUp(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping("/user/changePass")
    boolean changePass(@RequestParam("username") String username, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword);


}
