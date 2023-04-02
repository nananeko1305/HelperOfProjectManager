package com.codetribe.intership.model.dto;

import com.codetribe.intership.model.entity.Subtask;
import com.codetribe.intership.model.entity.enums.TaskStatus;
import com.codetribe.intership.service.SubtaskService;
import com.codetribe.intership.service.serviceImpl.SubtaskServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubtaskDTO {

    private int id;
    private String taskSpecification;
    private TaskStatus taskStatus;
    private Date deadline;
    private TaskDTO task;



    public SubtaskDTO(Subtask subtask) {
        this.id = subtask.getId();
        this.taskSpecification = subtask.getTaskSpecification();
        this.taskStatus = subtask.getTaskStatus();
        this.deadline = subtask.getDeadline();
        this.task = new TaskDTO(subtask.getTask(), 1);
    }

    public SubtaskDTO(Subtask subtask, int version) {
        this.id = subtask.getId();
        this.taskSpecification = subtask.getTaskSpecification();
        this.taskStatus = subtask.getTaskStatus();
        this.deadline = subtask.getDeadline();
    }

}
