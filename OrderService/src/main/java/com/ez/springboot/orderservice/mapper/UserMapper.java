package com.ez.springboot.orderservice.mapper;

import com.ez.springboot.workservice.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Classname UserMapper
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/26
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<User> getUser();

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findSingleUser(String username);

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User findSingleUserByUsernameAndPassword(String username, String password);

    @Insert("INSERT INTO user(id, username, password) VALUES (#{id}, #{username}, #{password})")
    boolean insertUser(User user);

    @Update("UPDATE user SET password = #{newPassword} WHERE username = #{username} AND password = #{oldPassword}")
    boolean updateUser(String username, String oldPassword, String newPassword);
}
