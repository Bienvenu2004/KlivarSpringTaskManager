package org.example.taskmanager.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
public class Status {
    @Id
    @SequenceGenerator(
            name = "status_sequence",
            sequenceName = "status_sequence",
            allocationSize = 1)//define the start of the sequence at 1
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "status_sequence")
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusDescription description;

    @OneToOne(mappedBy = "status")
    private Task task;

    public Status(StatusDescription description) {
        this.description = description;
    }

    public Status() {

    }
}
