package com.company.module.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.module.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByIdAndSerect(String id,String serect);
}
