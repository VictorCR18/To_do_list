package com.example.tarefas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Table(name = "tarefas")
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tarefa_id") // Nome da coluna personalizada para o ID
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "concluida", nullable = false)
    private boolean concluida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo != null ? titulo.trim() : null;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}
