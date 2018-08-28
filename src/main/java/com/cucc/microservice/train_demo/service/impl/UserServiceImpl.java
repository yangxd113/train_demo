package com.cucc.microservice.train_demo.service.impl;

import com.cucc.microservice.train_demo.dao.UserRepository;
import com.cucc.microservice.train_demo.domain.User;
import com.cucc.microservice.train_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }
}
