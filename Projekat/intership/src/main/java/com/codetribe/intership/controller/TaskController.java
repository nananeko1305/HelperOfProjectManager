package com.codetribe.intership.controller;

import com.codetribe.intership.model.dto.ProjectDTO;
import com.codetribe.intership.model.dto.TaskDTO;
import com.codetribe.intership.model.entity.Project;
import com.codetribe.intership.model.entity.Task;
import com.codetribe.intership.model.entity.enums.TaskStatus;
import com.codetribe.intership.service.ProjectService;
import com.codetribe.intership.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("{id}")
    public ResponseEntity<TaskDTO> GetOneTask(@PathVariable int id){
        return new ResponseEntity<>(new TaskDTO(taskService.GetOne(id)), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<TaskDTO> CreateTask(@RequestBody TaskDTO taskDTO) {

        Task task = new Task(taskDTO);
        Project project = projectService.GetOne(taskDTO.getProject().getId());
        task.setProject(project);
        taskService.CreateTask(task);
        taskDTO.setProject(new ProjectDTO(project));

        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }

    @PutMapping("{id}/{status}")
    public ResponseEntity<TaskDTO> ChangeTaskStatus(@PathVariable int id, @PathVariable String status){

        Task task = taskService.GetOne(id);
        task.setTaskStatus(TaskStatus.valueOf(status));
        taskService.UpdateTask(task);
        projectService.CheckTasks(task.getProject().getId());

        return new ResponseEntity<>(new TaskDTO(task), HttpStatus.OK);
    }

}
