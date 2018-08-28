package com.cucc.microservice.train_demo.service;

import com.cucc.microservice.train_demo.domain.User;

public interface UserService {
    void saveUser(User user);
    void deleteUser(Integer id);
}
