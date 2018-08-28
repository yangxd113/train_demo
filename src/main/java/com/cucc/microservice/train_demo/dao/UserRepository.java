package com.cucc.microservice.train_demo.dao;

import com.cucc.microservice.train_demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
