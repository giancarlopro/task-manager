package com.giancarlo.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giancarlo.taskmanager.model.User;

public interface Users extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
