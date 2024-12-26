package org.example.taskmanager.web.controller;

import org.example.taskmanager.repository.dao.TaskRepository;
import org.example.taskmanager.repository.entity.Task;
import org.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }


    @GetMapping
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @GetMapping(params = "taskId")
    public List<Task> getTaskById(@RequestParam Long taskId) {
        return taskService.getTaskById(taskId);
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
