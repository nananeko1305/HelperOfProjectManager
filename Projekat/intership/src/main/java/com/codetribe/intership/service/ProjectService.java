package com.codetribe.intership.service;

import com.codetribe.intership.model.dto.ProjectDTO;
import com.codetribe.intership.model.entity.Project;
import com.codetribe.intership.model.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    Project CreateProject(Project project);

    List<Project> GetAllProjects();

    void CheckTasks(int projectId);

    Project GetOne(int id);

    List<ProjectDTO> ProjectToDTO(List<Project> projects);

}
