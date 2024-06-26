package com.trabalhoFinal.TrabFinalJava.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class InscricaoUsuarioAtividade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "atividade_id", nullable = false)
    private Atividade atividade;
}
