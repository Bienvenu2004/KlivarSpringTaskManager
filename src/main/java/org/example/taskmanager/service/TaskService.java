package org.example.taskmanager.service;

import jakarta.transaction.Transactional;
import org.example.taskmanager.repository.dao.TaskRepository;
import org.example.taskmanager.repository.entity.Task;
import org.hibernate.tool.schema.spi.SqlScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;

    }

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public List<Task> getTaskById(Long taskId){
        return taskRepository.getTasksById(taskId);
    }



    public void addTask(Task task){
        if(taskRepository.findTaskByTitre(task.getTitre()).isEmpty()){
            taskRepository.save(task);
        }
        else {
            throw new SqlScriptException("task title taken already");
        }
    }

    public void deleteTask(Long taskId){
        boolean exists = taskRepository.existsById(taskId);
        if(!exists){
            throw new SqlScriptException("Task with id " + taskId + " was not found");
        }
        taskRepository.deleteById(taskId);
    }

    @Transactional
    public void updateTask(Long taskId, String titre, String description, int priorite){
        Task task = taskRepository.findById(taskId).orElseThrow( () -> new SqlScriptException("Task with id " + taskId + " does not exist") );
        if(
                titre != null && //if the firstName is not equals to null
                        !titre.isEmpty() && //if the firstName is not empty
                        !Objects.equals(task.getTitre(), titre )) { //if the name provided is not the same as the current one
            task.setTitre(titre);//then we just set the firstName
        }

        if(
                description != null && //if the firstName is not equals to null
                        !description.isEmpty() && //if the firstName is not empty
                        !Objects.equals(task.getDescription(), description )) { //if the name provided is not the same as the current one
            task.setDescription(description);//then we just set the firstName
        }

        if(
                !Objects.equals(task.getPriorite(), priorite )) { //if the name provided is not the same as the current one
            task.setDescription(description);//then we just set the priorite
        }

    }
}
