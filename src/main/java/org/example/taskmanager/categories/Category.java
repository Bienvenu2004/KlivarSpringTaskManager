package org.example.taskmanager.categories;

import jakarta.persistence.*;
import lombok.Data;
import org.example.taskmanager.task.Task;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
public class Category {
    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1)//define the start of the sequence at 1
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "category_sequence")
    private Long id;

    public Category(CategoryName name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    private CategoryName name;

    @OneToOne(mappedBy = "category")
    private Task task;

    public Category() {

    }
}
