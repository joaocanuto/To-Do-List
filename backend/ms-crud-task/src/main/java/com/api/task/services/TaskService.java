package com.api.task.services;

import com.api.task.dto.TaskDTO;
import com.api.task.entities.Task;
import com.api.task.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    // chamar a busca de usuario e retornar um dto para o controlador.

    // injetando o TaskRepository
    @Autowired
    private TaskRepository repository;

    @Transactional
    public TaskDTO findById(Long id){
        Task entity = repository.findById(id).get();
        TaskDTO dto = new TaskDTO(entity);
        return dto;
    }
    @Transactional
    public List<TaskDTO>  findByNome(String nome){
        List<Task> taskList = repository.findByNome(nome);
        List<TaskDTO> taskDTOList = new ArrayList<>();
        taskList.forEach(task -> {
            TaskDTO dto = new TaskDTO(task);
            taskDTOList.add(dto);
        });
        return taskDTOList;
    }


}
