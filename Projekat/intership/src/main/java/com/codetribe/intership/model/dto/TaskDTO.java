package com.codetribe.intership.model.dto;

import com.codetribe.intership.model.entity.Project;
import com.codetribe.intership.model.entity.Task;
import com.codetribe.intership.model.entity.enums.TaskStatus;
import com.codetribe.intership.service.SubtaskService;
import com.codetribe.intership.service.serviceImpl.SubtaskServiceImpl;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private int id;
    private String taskSpecification;
    private LocalDate deadline;
    private TaskStatus taskStatus;
    private ProjectDTO project;
    private List<SubtaskDTO> subtasks;

    public TaskDTO(Task task){
        SubtaskService subtaskService = new SubtaskServiceImpl();
        this.id = task.getId();
        this.taskSpecification = task.getTaskSpecification();
        this.taskStatus = task.getTaskStatus();
        this.deadline = task.getDeadline();
        this.project = new ProjectDTO(task.getProject(), 1);
        this.subtasks = subtaskService.SubtaskToDTO(task.getSubtaskList());
    }

    public TaskDTO(Task task, int version){
        this.id = task.getId();
        this.taskSpecification = task.getTaskSpecification();
        this.deadline = task.getDeadline();
        this.project = new ProjectDTO(task.getProject(), 1);
    }

}
