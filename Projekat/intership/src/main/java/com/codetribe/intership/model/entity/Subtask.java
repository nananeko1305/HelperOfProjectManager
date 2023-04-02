package com.codetribe.intership.model.entity;

import com.codetribe.intership.model.dto.SubtaskDTO;
import com.codetribe.intership.model.dto.TaskDTO;
import com.codetribe.intership.model.entity.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subtasks")
public class Subtask{

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "taskSpecification", nullable = false)
    private String taskSpecification;

    @Column(name = "taskStatus", nullable = false)
    private TaskStatus taskStatus;

    @Column(name = "deadline", nullable = false)
    private Date deadline;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taskId", referencedColumnName = "id", nullable = false)
    private Task task;

    public Subtask(SubtaskDTO subtaskDTO){
        this.id = subtaskDTO.getId();
        this.taskSpecification = subtaskDTO.getTaskSpecification();
        this.taskStatus = subtaskDTO.getTaskStatus();
        this.deadline = subtaskDTO.getDeadline();
        this.task = new Task(subtaskDTO.getTask());
    }
}
