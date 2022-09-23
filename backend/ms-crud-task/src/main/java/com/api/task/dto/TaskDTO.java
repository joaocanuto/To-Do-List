package com.api.task.dto;

import com.api.task.entities.Task;

public class TaskDTO {

    private Long id;
    private String nome;

    public TaskDTO(){
    }

    public TaskDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public TaskDTO(Task task) {
        id = task.getId();
        nome = task.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
