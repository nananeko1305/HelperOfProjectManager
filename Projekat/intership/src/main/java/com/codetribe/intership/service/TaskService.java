package com.codetribe.intership.service;

import com.codetribe.intership.model.dto.TaskDTO;
import com.codetribe.intership.model.entity.Task;
import com.codetribe.intership.model.entity.enums.TaskStatus;

import java.util.List;

public interface TaskService {

    void CreateTask(Task task);

    void UpdateTask(Task task);

    void CheckSubtasks(int taskId);

    List<TaskDTO> TaskToDTO(List<Task> tasks);

    Task GetOne(int id);




}
