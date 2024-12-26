package org.example.taskmanager.web.controller;

import org.example.taskmanager.repository.dao.TaskDao;
import org.example.taskmanager.repository.entity.Task;
import org.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskDao taskDao;

    @Autowired
    public TaskController(TaskService taskService, TaskDao taskDao) {
        this.taskService = taskService;
        this.taskDao = taskDao;
    }


    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping(params = "taskId")
    public List<Task> getAllTaskById(@RequestParam Long taskId) {
        return taskService.getAllTaskById(taskId);
    }

    @PostMapping
    public void addTask(@RequestBody Task task){
        taskService.addTask(task);
    }

    @DeleteMapping(path = "{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId){
        taskService.deleteTask(taskId);

    }

    @PutMapping(path = "{taskId}")
    public void updateTask(
            @PathVariable("taskId") Long taskId,
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) int priorite
    ){

        taskService.updateTask(taskId, titre, description, priorite);
    }

}
