package com.codetribe.intership.model.dto;

import com.codetribe.intership.model.entity.Project;
import com.codetribe.intership.model.entity.enums.Status;
import com.codetribe.intership.service.TaskService;
import com.codetribe.intership.service.serviceImpl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate deadline;
    private String clientName;
    private String description;
    private Status status;
    private List<TaskDTO> tasks;

    public ProjectDTO(Project project){
        TaskService taskService = new TaskServiceImpl();
        this.id = project.getId();
        this.name = project.getName();
        this.startDate = project.getStartDate();
        this.deadline = project.getDeadline();
        this.clientName = project.getClientName();
        this.description = project.getDescription();
        this.status = project.getStatus();
        if(project.getTasks().isEmpty()){
            this.tasks = null;
        }else {
            this.tasks = taskService.TaskToDTO(project.getTasks());
        }
    }


    public ProjectDTO(Project project, int version){
        TaskService taskService = new TaskServiceImpl();
        this.id = project.getId();
        this.name = project.getName();
        this.startDate = project.getStartDate();
        this.deadline = project.getDeadline();
        this.clientName = project.getClientName();
        this.description = project.getDescription();
        this.status = project.getStatus();
    }

}
