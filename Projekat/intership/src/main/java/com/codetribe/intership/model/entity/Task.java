package com.codetribe.intership.model.entity;

import com.codetribe.intership.model.dto.TaskDTO;
import com.codetribe.intership.model.entity.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "taskSpecification", nullable = false)
    private String taskSpecification;

    @Column(name = "taskStatus", nullable = false)
    private TaskStatus taskStatus;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Subtask> subtaskList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    private Project project;

    public Task(TaskDTO taskDTO){
        this.id = taskDTO.getId();
        this.taskSpecification = taskDTO.getTaskSpecification();
        this.deadline = taskDTO.getDeadline();
    }

}
