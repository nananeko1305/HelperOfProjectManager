package com.codetribe.intership.service.serviceImpl;

import com.codetribe.intership.model.dto.TaskDTO;
import com.codetribe.intership.model.entity.Subtask;
import com.codetribe.intership.model.entity.Task;
import com.codetribe.intership.model.entity.enums.Status;
import com.codetribe.intership.model.entity.enums.TaskStatus;
import com.codetribe.intership.repository.TaskRepository;
import com.codetribe.intership.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void CreateTask(Task task) {
        task.setTaskStatus(TaskStatus.TO_DO);
        taskRepository.save(task);
    }

    @Override
    public void UpdateTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void CheckSubtasks(int taskId) {
        Task task = GetOne(taskId);
        int TODOCounter = 0;
        int DONECounter = 0;
        int OTHERCounter = 0;
        for(Subtask subtask : task.getSubtaskList()){
            if (subtask.getTaskStatus() == TaskStatus.TO_DO){
                TODOCounter++;
            }else if (subtask.getTaskStatus() == TaskStatus.DONE){
                DONECounter++;
            }else {
                OTHERCounter++;
            }
        }
        if (DONECounter == 0 && OTHERCounter == 0){
            task.setTaskStatus(TaskStatus.TO_DO);
            taskRepository.save(task);
        } else if(OTHERCounter == 0 && TODOCounter == 0){
            task.setTaskStatus(TaskStatus.DONE);
            taskRepository.save(task);
        }else if (DONECounter > 0 || OTHERCounter > 0){
            task.setTaskStatus(TaskStatus.IN_PROGRESS);
            taskRepository.save(task);
        }
    }

    @Override
    public List<TaskDTO> TaskToDTO(List<Task> tasks) {
        List<TaskDTO> taskDTOList = new ArrayList<>();
        for (Task task :tasks){
            TaskDTO taskDTO = new TaskDTO(task);
            taskDTOList.add(taskDTO);
        }
        return taskDTOList;
    }

    @Override
    public Task GetOne(int id) {
        return taskRepository.findById(id).orElseGet(null);
    }

}
