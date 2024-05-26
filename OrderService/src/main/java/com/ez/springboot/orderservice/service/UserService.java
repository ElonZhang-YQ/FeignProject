package com.ez.springboot.orderservice.service;

import com.ez.springboot.workservice.bean.User;

import java.util.List;

/**
 * @Classname UserService
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/26
 */
public interface UserService {

    List<User> findAllUsers();

    User findUserByName(String username);

    boolean signIn(String username, String password);

    User signUp(String username, String password);

    boolean modifyPassword(String username, String oldPassword, String newPassword);
}
