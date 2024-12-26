package org.example.taskmanager.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)//define the start of the sequence at 1
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence")
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {

    }
}
