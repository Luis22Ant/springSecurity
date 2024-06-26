package com.trabalhoFinal.TrabFinalJava.Models;

import jakarta.persistence.*;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "membro_id", nullable = false)
    private Usuario membro;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String dataPagamento;

    @Column(nullable = false)
    private boolean pago = false;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getMembro() {
        return membro;
    }

    public void setMembro(Usuario membro) {
        this.membro = membro;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}

