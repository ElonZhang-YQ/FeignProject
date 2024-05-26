package com.ez.springboot.workservice.controller;

import com.ez.springboot.workservice.bean.User;
import com.ez.springboot.workservice.client.UserFeignClient;
import com.ez.springboot.workservice.producer.UserMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname UserFeignController
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/26
 */
@RestController
@RequestMapping("/feign")
public class UserFeignController {

    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private UserMessageProducer userMessageProducer;

    @RequestMapping("/findAllUser")
    public List<User> findUser() {

        return userFeignClient.findUser();
    }

    @RequestMapping("/findSingle")
    public User findUser(String username) {

        return userFeignClient.findUser(username);
    }

    @RequestMapping("/signIn")
    public boolean signIn(String username, String password) {
        boolean rst = userFeignClient.signIn(username, password);
        if (rst) {
            userMessageProducer.produceUserLoginMessage("用户：" + username + " 登录成功！");
        }
        return rst;
    }

    @RequestMapping("/signUp")
    public User signUp(String username, String password) {
        User rst = userFeignClient.signUp(username, password);
        userMessageProducer.produceUserSignUpMessage(rst);
        return rst;
    }

    @RequestMapping("changePass")
    public boolean changePass(String username, String oldPassword, String newPassword) {
        boolean rst = userFeignClient.changePass(username, oldPassword, newPassword);
        if (rst) {
            userMessageProducer.produceUserUpdateMessage("用户：" + username + " 修改密码成功！");
        }
        return rst;
    }
}
