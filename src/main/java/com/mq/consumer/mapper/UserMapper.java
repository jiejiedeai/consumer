package com.mq.consumer.mapper;

import com.mq.model.entity.User;

import java.util.List;

public interface UserMapper {
    List<User> searchUsers();

    boolean saveUser(User user);
}
