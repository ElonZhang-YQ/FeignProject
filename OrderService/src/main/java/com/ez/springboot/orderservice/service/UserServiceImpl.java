package com.ez.springboot.orderservice.service;

import com.ez.springboot.orderservice.mapper.UserMapper;
import com.ez.springboot.orderservice.utils.IdUtils;
import com.ez.springboot.workservice.bean.User;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/5/26
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    private static final String USER_KEY = "USER_INFO_KEY:";
    private static final String ID_KEY = "ID_INFO_KEY:";

    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUsers() {
        return userMapper.getUser();
    }

    @Override
    public User findUserByName(String username) {
        User rst = null;
        rst = (User) redissonClient.getBucket(USER_KEY + username).get();
        if (rst == null) {
            // 缓存中没有拿到数据，那就从数据库拿
            rst = userMapper.findSingleUser(username);
            // 拿到数据后，将数据放入到缓存中
            if (rst != null) {
                redissonClient.getBucket(USER_KEY + username).set(rst, Duration.ofMinutes(30));
            }
        }
        return rst;
    }

    @Override
    public boolean signIn(String username, String password) {
        User user = (User) redissonClient.getBucket(USER_KEY + username).get();
        if (user == null) {
            // 缓存中没有，查DB
            user = userMapper.findSingleUserByUsernameAndPassword(username, password);
            if (user != null) {
                // DB查询得到，将查询信息放入缓存中
                redissonClient.getBucket(USER_KEY + username).set(user, Duration.ofMinutes(30));
                return true;
            }
        } else {
            // 即使在缓存中查询得到，也需要判断密码是否正确。正常逻辑业务中，通过cookie的登录状态去判断，而不是这样
            return user.getPassword().equals(password);
        }
        return false;
    }

    @Override
    public User signUp(String username, String password) {
        /**
         * 有一个逻辑去创建分布式ID
         * 根据雪花算法逻辑可以替换得到，根据时间+机房信息+机器信息+分布式ID
         * 这样生成的分布式ID是有序自增的，可以作为主键使用
         */
        Long userId = IdUtils.snowflakeId(redissonClient.getAtomicLong(ID_KEY).getAndIncrement());
        User user = new User(userId, username, password);
        return userMapper.insertUser(user) ? user : null;
    }

    @Override
    public boolean modifyPassword(String username, String oldPassword, String newPassword) {
        User user = (User) redissonClient.getBucket(USER_KEY + username).get();
        if (user != null && user.getPassword().equals(oldPassword)) {
            boolean result = userMapper.updateUser(username, oldPassword, newPassword);
            if (result) {
                redissonClient.getBucket(USER_KEY + username).delete();
            }
            return result;
        }
        return false;
    }
}
