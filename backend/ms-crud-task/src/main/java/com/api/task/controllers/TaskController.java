package com.api.task.controllers;

import com.api.task.dto.TaskDTO;
import com.api.task.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping(path = "/{id}")
    ResponseEntity<Optional<TaskDTO>> getById(@PathVariable Long id){
        Optional<TaskDTO> taskDTO;
        try{
            taskDTO = Optional.ofNullable(service.findById(id));
            return new ResponseEntity<Optional<TaskDTO>>(taskDTO, HttpStatus.OK);
        } catch (NoSuchElementException nsee){
            return new ResponseEntity<Optional<TaskDTO>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/search/{nome}")
    ResponseEntity<Optional<List<TaskDTO>>> getByNome(@PathVariable String nome){
        Optional<List<TaskDTO>> taskDTOList;
        try{
            taskDTOList = Optional.ofNullable(service.findByNome(nome));
            return new ResponseEntity<Optional<List<TaskDTO>>>(taskDTOList, HttpStatus.OK);
        } catch (NoSuchElementException nsee){
            return new ResponseEntity<Optional<List<TaskDTO>>>(HttpStatus.NOT_FOUND);
        }
    }

}
