package org.example.taskmanager.commandLineRunner;

import org.example.taskmanager.categories.Category;
import org.example.taskmanager.categories.CategoryName;
import org.example.taskmanager.categories.CategoryRepository;
import org.example.taskmanager.status.Status;
import org.example.taskmanager.status.StatusDescription;
import org.example.taskmanager.status.StatusRepository;
import org.example.taskmanager.task.Task;
import org.example.taskmanager.task.TaskRepository;
import org.example.taskmanager.user.User;
import org.example.taskmanager.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception{
        User joseph = new User("Joseph", "Joseph@Gmail.com");

        //Status creation and save
        Status waiting = new Status(StatusDescription.waiting);
        Status completed = new Status(StatusDescription.completed);
        Status executing = new Status(StatusDescription.executing);
        Status notStarted = new Status(StatusDescription.notStarted);
        statusRepository.saveAll(List.of(
                notStarted,
                waiting,
                executing,
                completed));

        //Category creation and save
        Category development = new Category(CategoryName.development);
        Category conception = new Category(CategoryName.conception);
        categoryRepository.saveAll(List.of(
                development,
                conception
        ));


        //Task creation and save
        Task sleep = new Task(
                "sleep",
                "To grow well",
                LocalDate.now(),
                LocalDate.of(2024, 12, 22),
                1
        );

        Task jump = new Task(
                "jump",
                "To grow Muscle",
                LocalDate.now(),
                LocalDate.of(2024, 12, 30),
                1
        );

        sleep.setStatus(waiting);
        sleep.setUser(joseph);
        sleep.setCategory(conception);

        jump.setStatus(executing);
        jump.setUser(joseph);
        jump.setCategory(development);




        joseph.setTasks(List.of(
                sleep,
                jump));

        userRepository.save(joseph);
        taskRepository.saveAll(List.of(
                sleep,
                jump));

    }
}
