package com.trabalhoFinal.TrabFinalJava.Repository;

import com.trabalhoFinal.TrabFinalJava.Models.Atividade;
import com.trabalhoFinal.TrabFinalJava.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    List<Atividade> findAll();

    Optional<Atividade> findById(Long id);

    Atividade save(Atividade atividade);

    void deleteById(Long id);

}
