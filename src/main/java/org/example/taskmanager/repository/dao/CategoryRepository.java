package org.example.taskmanager.repository.dao;

import org.example.taskmanager.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
