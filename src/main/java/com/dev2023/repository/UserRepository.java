package com.dev2023.repository;

import com.dev2023.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String Email);
    User findByPhone(String phone);
}

