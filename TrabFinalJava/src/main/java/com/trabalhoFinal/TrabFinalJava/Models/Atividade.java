package com.trabalhoFinal.TrabFinalJava.Models;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "instrutor_id", nullable = false)
    private Usuario instrutor;

    @Column(nullable = false)
    private String dataHora;


    @ManyToMany
    @JoinTable(
            name = "atividade_membro",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "membro_id")
    )
    private Set<Usuario> membrosInscritos = new HashSet<>();
}