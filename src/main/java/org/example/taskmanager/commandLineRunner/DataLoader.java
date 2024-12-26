package org.example.taskmanager.commandLineRunner;

import org.example.taskmanager.repository.entity.Category;
import org.example.taskmanager.repository.entity.CategoryName;
import org.example.taskmanager.repository.dao.CategoryDao;
import org.example.taskmanager.repository.entity.Status;
import org.example.taskmanager.repository.entity.StatusDescription;
import org.example.taskmanager.repository.dao.StatusDao;
import org.example.taskmanager.repository.entity.Task;
import org.example.taskmanager.repository.dao.TaskDao;
import org.example.taskmanager.repository.entity.User;
import org.example.taskmanager.repository.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private TaskDao taskDao;

    @Autowired
    private StatusDao statusDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void run(String... args) throws Exception{
        User joseph = new User("Joseph", "Joseph@Gmail.com");

        //Status creation and save
        Status waiting = new Status(StatusDescription.waiting);
        Status completed = new Status(StatusDescription.completed);
        Status executing = new Status(StatusDescription.executing);
        Status notStarted = new Status(StatusDescription.notStarted);
        statusDao.saveAll(List.of(
                notStarted,
                waiting,
                executing,
                completed));

        //Category creation and save
        Category development = new Category(CategoryName.development);
        Category conception = new Category(CategoryName.conception);
        categoryDao.saveAll(List.of(
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

        userDao.save(joseph);
        taskDao.saveAll(List.of(
                sleep,
                jump));

    }
}
