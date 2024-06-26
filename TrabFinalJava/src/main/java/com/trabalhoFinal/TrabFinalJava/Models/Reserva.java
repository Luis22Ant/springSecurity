package com.trabalhoFinal.TrabFinalJava.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "membro_id", nullable = false)
    private Usuario membro;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private String data;


}

