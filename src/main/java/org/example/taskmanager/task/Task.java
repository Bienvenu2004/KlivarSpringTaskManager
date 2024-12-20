package org.example.taskmanager.task;

import jakarta.persistence.*;
import lombok.Data;
import org.example.taskmanager.categories.Category;
import org.example.taskmanager.status.Status;
import org.example.taskmanager.user.User;

import java.time.LocalDate;
import java.util.Date;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
public class Task {

    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1)//define the start of the sequence at 1
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "task_sequence")
    @Column(name = "task_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String titre;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate date_creation;

    @Column(nullable = false)
    private LocalDate date_echeance;

    @Column(nullable = false)
    private int priorite;
    //join clolumns
    //private int status_id;
    //private int category_id;
    //private int user_id;

    public Task(String titre, String description, LocalDate date_creation, LocalDate date_echeance, int priorite) {
        this.description = description;
        this.titre = titre;
        this.date_creation = date_creation;
        this.date_echeance = date_echeance;
        this.priorite = priorite;
    }

    public Task() {

    }

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;

}
