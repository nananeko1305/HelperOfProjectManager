package com.codetribe.intership.model.entity;

import com.codetribe.intership.model.dto.ProjectDTO;
import com.codetribe.intership.model.entity.enums.Status;
import com.codetribe.intership.repository.ProjectRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
public class Project {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "clientName", nullable = false)
    private String clientName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status", nullable = false)
    private Status status;


    @OneToMany(mappedBy = "project", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Task> tasks = new ArrayList<>();


    public Project(ProjectDTO projectDTO){
        this.id = projectDTO.getId();
        this.name = projectDTO.getName();
        this.clientName = projectDTO.getClientName();
        this.startDate = projectDTO.getStartDate();
        this.deadline = projectDTO.getDeadline();
        this.description = projectDTO.getDescription();
        this.status = projectDTO.getStatus();
    }


}
