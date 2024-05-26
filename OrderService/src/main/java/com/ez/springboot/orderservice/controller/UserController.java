package com.ez.springboot.orderservice.controller;

import com.ez.springboot.orderservice.service.UserService;
import com.ez.springboot.workservice.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname UserController
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/26
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUser")
    public List<User> findUser() {

        return userService.findAllUsers();
    }

    @RequestMapping("/findSingle")
    public User findUser(String username) {

        return userService.findUserByName(username);
    }

    @RequestMapping("/signIn")
    public boolean signIn(String username, String password) {

        return userService.signIn(username, password);
    }

    @RequestMapping("/signUp")
    public User signUp(String username, String password) {

        return userService.signUp(username, password);
    }

    @RequestMapping("/changePass")
    public boolean changePassword(String username, String oldPassword, String newPassword) {

        return userService.modifyPassword(username, oldPassword, newPassword);
    }
}
