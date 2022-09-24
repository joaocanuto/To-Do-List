package com.api.task.controllers;

import com.api.task.dto.TaskDTO;
import com.api.task.services.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/task")
@Api("Api de Tasks")
public class TaskController {

    @Autowired
    private TaskService service;


    @ApiOperation("Seleciona uma task pelo ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a task pelo ID"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/{id}" , method = RequestMethod.GET , produces ="application/json")
    ResponseEntity<TaskDTO> getById(@PathVariable Long id){
        TaskDTO taskDTO;
        try{
            taskDTO = service.findById(id);
            return new ResponseEntity<TaskDTO>(taskDTO, HttpStatus.OK);
        } catch (NoSuchElementException nsee){
            return new ResponseEntity<TaskDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/search/{nome}", method = RequestMethod.GET , produces ="application/json")
    @ApiOperation("Busca tasks pelo nome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a task pelo nome"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    ResponseEntity<List<TaskDTO>> getByNome(@PathVariable String nome){
        List<TaskDTO> taskDTOList;
        try{
            taskDTOList = service.findByNome(nome);
            return new ResponseEntity<List<TaskDTO>>(taskDTOList, HttpStatus.OK);
        } catch (NoSuchElementException nsee){
            return new ResponseEntity<List<TaskDTO>>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET , produces ="application/json")
    @ApiOperation("Retorna todas as Tasks")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todas as tasks"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    ResponseEntity<List<TaskDTO>> getAll(){
        List<TaskDTO> taskDTOList;
        try{
            taskDTOList = service.findAll();
            return new ResponseEntity<List<TaskDTO>>(taskDTOList, HttpStatus.OK);
        } catch (NoSuchElementException nsee){
            return new ResponseEntity<List<TaskDTO>>(HttpStatus.NOT_FOUND);
        }
    }
}
