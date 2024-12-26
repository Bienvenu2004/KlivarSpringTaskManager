package org.example.taskmanager.repository.dao;

import org.example.taskmanager.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}