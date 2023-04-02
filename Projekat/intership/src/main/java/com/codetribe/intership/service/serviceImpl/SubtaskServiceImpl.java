package com.codetribe.intership.service.serviceImpl;

import com.codetribe.intership.model.dto.SubtaskDTO;
import com.codetribe.intership.model.entity.Subtask;
import com.codetribe.intership.repository.SubtaskRepository;
import com.codetribe.intership.service.SubtaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubtaskServiceImpl implements SubtaskService {

    @Autowired
    private SubtaskRepository subtaskRepository;
    @Override
    public List<Subtask> GetAllSubtasks() {
        return subtaskRepository.findAll();
    }

    @Override
    public Subtask GetOne(int id) {
        return subtaskRepository.findById(id).orElseGet(null);
    }

    @Override
    public Subtask CreateSubtask(Subtask subtask) {
        return subtaskRepository.save(subtask);
    }

    @Override
    public List<SubtaskDTO> SubtaskToDTO(List<Subtask> subtasks) {
        List<SubtaskDTO> subtaskDTOS = new ArrayList<>();
        for (Subtask subtask : subtasks){
            subtaskDTOS.add(new SubtaskDTO(subtask));
        }
    return subtaskDTOS;
    }

    @Override
    public void UpdateSubtask(Subtask subtask) {
        subtaskRepository.save(subtask);
    }
}
