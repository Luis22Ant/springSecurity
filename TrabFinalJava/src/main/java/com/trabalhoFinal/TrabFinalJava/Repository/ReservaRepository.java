package com.trabalhoFinal.TrabFinalJava.Repository;

import com.trabalhoFinal.TrabFinalJava.Models.Reserva;
import com.trabalhoFinal.TrabFinalJava.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findAll();

    Optional<Reserva> findById(Long id);

    Reserva save(Reserva reserva);

    void deleteById(Long id);

    List<Reserva> findByMembroId(Long membroId);

    boolean existsByItemAndData(Item item, String data);
}
