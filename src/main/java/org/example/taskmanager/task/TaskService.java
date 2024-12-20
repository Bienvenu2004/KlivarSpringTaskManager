package org.example.taskmanager.task;

import jakarta.transaction.Transactional;
import org.hibernate.tool.schema.spi.SqlScriptException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public void addTask(Task task){
        Optional<Task> taskOptional = taskRepository.findTaskByTitre(task.getTitre());
        if(taskOptional.isPresent()){
            throw new SqlScriptException("This titre already exist");
        }
        taskRepository.save(task);

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
