package org.example.taskmanager.repository.dao;

import org.example.taskmanager.repository.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
