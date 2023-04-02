package com.codetribe.intership.service;

import com.codetribe.intership.model.dto.SubtaskDTO;
import com.codetribe.intership.model.entity.Subtask;

import java.util.List;

public interface SubtaskService {

    List<Subtask> GetAllSubtasks();

    Subtask GetOne(int id);

    Subtask CreateSubtask(Subtask subtask);

    List<SubtaskDTO> SubtaskToDTO(List<Subtask> subtasks);

    void UpdateSubtask(Subtask subtask);
}
