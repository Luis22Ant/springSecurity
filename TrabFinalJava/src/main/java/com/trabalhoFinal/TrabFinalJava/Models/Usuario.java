package com.trabalhoFinal.TrabFinalJava.Models;

import jakarta.persistence.*;
import com.trabalhoFinal.TrabFinalJava.Enums.ETipoUser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ETipoUser role;

    @Column(nullable = false)
    private boolean ativo = true;

    @OneToMany(mappedBy = "membro",fetch = FetchType.EAGER)
    private Set<Reserva> reservas = new HashSet<>();

    @OneToMany(mappedBy = "membro",fetch = FetchType.EAGER)
    private Set<Pagamento> pagamentos = new HashSet<>();

    @OneToMany(mappedBy = "instrutor",fetch = FetchType.EAGER)
    private Set<Atividade> atividades = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ETipoUser getRole() {
        return role;
    }

    public void setRole(ETipoUser role) {
        this.role = role;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Set<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(Set<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public Set<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(Set<Atividade> atividades) {
        this.atividades = atividades;
    }
}
