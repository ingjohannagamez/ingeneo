package com.ingeneo.logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ingeneo.logistica.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}