package com.codetribe.intership.controller;

import com.codetribe.intership.model.dto.ProjectDTO;
import com.codetribe.intership.model.entity.Project;
import com.codetribe.intership.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping()
    public ResponseEntity<List<ProjectDTO>> GetProjects() {
        return new ResponseEntity<>(projectService.ProjectToDTO(projectService.GetAllProjects()) , HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectDTO> GetOne(@PathVariable int id) {
        return new ResponseEntity<>(new ProjectDTO(projectService.GetOne(id)), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Project> CreateProject(@RequestBody Project project) {
        return new ResponseEntity<>(projectService.CreateProject(project), HttpStatus.OK);
    }

}
