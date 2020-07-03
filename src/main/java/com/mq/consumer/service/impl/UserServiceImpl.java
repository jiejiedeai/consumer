package com.mq.consumer.service.impl;

import com.mq.consumer.mapper.UserMapper;
import com.mq.consumer.service.UserService;
import com.mq.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> searchUsers() {
        return userMapper.searchUsers();
    }
}
