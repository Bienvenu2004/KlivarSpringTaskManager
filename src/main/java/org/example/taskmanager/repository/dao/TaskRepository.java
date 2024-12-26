package org.example.taskmanager.repository.dao;

import org.example.taskmanager.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTaskByTitre(String titre);
    List<Task> findTaskById(Long taskId);

    List<Task> getTasksById(Long id);
}
