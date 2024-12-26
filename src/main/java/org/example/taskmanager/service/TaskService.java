package org.example.taskmanager.service;

import jakarta.transaction.Transactional;
import org.example.taskmanager.repository.dao.TaskDao;
import org.example.taskmanager.repository.entity.Task;
import org.hibernate.tool.schema.spi.SqlScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    private final TaskDao taskDao;

    @Autowired
    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;

    }

    public List<Task> getAllTasks(){
        return taskDao.findAll();
    }

    public List<Task> getAllTaskById(Long taskId){
        return taskDao.getTasksById(taskId);
    }



    public void addTask(Task task){
        if(taskDao.findTaskByTitre(task.getTitre()).isEmpty()){
            taskDao.save(task);
        }
        else {
            throw new SqlScriptException("task title taken already");
        }
    }

    public void deleteTask(Long taskId){
        boolean exists = taskDao.existsById(taskId);
        if(!exists){
            throw new SqlScriptException("Task with id " + taskId + " was not found");
        }
        taskDao.deleteById(taskId);
    }

    @Transactional
    public void updateTask(Long taskId, String titre, String description, int priorite){
        Task task = taskDao.findById(taskId).orElseThrow( () -> new SqlScriptException("Task with id " + taskId + " does not exist") );
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
