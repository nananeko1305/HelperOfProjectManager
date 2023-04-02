package com.codetribe.intership.service.serviceImpl;

import com.codetribe.intership.model.dto.ProjectDTO;
import com.codetribe.intership.model.entity.Project;
import com.codetribe.intership.model.entity.Task;
import com.codetribe.intership.model.entity.enums.Status;
import com.codetribe.intership.model.entity.enums.TaskStatus;
import com.codetribe.intership.repository.ProjectRepository;
import com.codetribe.intership.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project CreateProject(Project project) {
        project.setStatus(Status.PLANNING);
        return projectRepository.save(project);
    }

    @Override
    public List<Project> GetAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void CheckTasks(int projectId) {
        Project project = GetOne(projectId);
        int TODOCounter = 0;
        int DONECounter = 0;
        int OTHERCounter = 0;
        for(Task task : project.getTasks()){
            if (task.getTaskStatus() == TaskStatus.TO_DO){
                TODOCounter++;
            }else if (task.getTaskStatus() == TaskStatus.DONE){
                DONECounter++;
            }else {
                OTHERCounter++;
            }
        }
        if (TODOCounter > 0 && DONECounter == 0 && OTHERCounter == 0){
            project.setStatus(Status.PLANNING);
            projectRepository.save(project);
        } else if (TODOCounter == 0 && OTHERCounter == 0 && DONECounter != 0){
            project.setStatus(Status.FINISHED);
            projectRepository.save(project);
        } else if (DONECounter > 0 || OTHERCounter > 0){
            project.setStatus(Status.DEVELOPMENT);
            projectRepository.save(project);
        }
    }

    @Override
    public Project GetOne(int id){
        return projectRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<ProjectDTO> ProjectToDTO(List<Project> projects){
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        for (Project project : projects){
            projectDTOList.add(new ProjectDTO(project));
        }

        return projectDTOList;
    }
}
