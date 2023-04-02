package com.codetribe.intership.controller;

import com.codetribe.intership.model.dto.SubtaskDTO;
import com.codetribe.intership.model.dto.TaskDTO;
import com.codetribe.intership.model.entity.Project;
import com.codetribe.intership.model.entity.Subtask;
import com.codetribe.intership.model.entity.Task;
import com.codetribe.intership.model.entity.enums.TaskStatus;
import com.codetribe.intership.service.ProjectService;
import com.codetribe.intership.service.SubtaskService;
import com.codetribe.intership.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subtasks")
public class SubtaskController {

    @Autowired
    private SubtaskService subtaskService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @GetMapping()
    public ResponseEntity<List<Subtask>> GetSubtasks() {

        List<Subtask> subtasks = subtaskService.GetAllSubtasks();

        System.out.println("GetAllProjects");
        return new ResponseEntity<>(subtasks, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<SubtaskDTO> GetOneTask(@PathVariable int id){
        return new ResponseEntity<>(new SubtaskDTO(subtaskService.GetOne(id)), HttpStatus.OK);
    }
    @PostMapping(consumes = "application/json")
    public ResponseEntity<SubtaskDTO> CreateSubtask(@RequestBody SubtaskDTO subtaskDTO) {
        Subtask subtask = new Subtask(subtaskDTO);
        return new ResponseEntity<>(new SubtaskDTO(subtaskService.CreateSubtask(subtask)), HttpStatus.OK);
    }

    @PutMapping("{id}/{status}")
    public ResponseEntity<SubtaskDTO> ChangeTaskStatus(@PathVariable int id, @PathVariable String status){

        Subtask subtask = subtaskService.GetOne(id);
        subtask.setTaskStatus(TaskStatus.valueOf(status));
        subtaskService.UpdateSubtask(subtask);
        taskService.CheckSubtasks(subtask.getTask().getId());
        projectService.CheckTasks(subtask.getTask().getProject().getId());

        return new ResponseEntity<>(new SubtaskDTO(subtask), HttpStatus.OK);
    }

}
