package com.trabalhoFinal.TrabFinalJava.Services;

import com.trabalhoFinal.TrabFinalJava.Models.Item;
import com.trabalhoFinal.TrabFinalJava.Models.Reserva;
import com.trabalhoFinal.TrabFinalJava.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void deleteById(Long id) {
        reservaRepository.deleteById(id);
    }

    public List<Reserva> findByUserId(Long userId) {
        return reservaRepository.findByMembroId(userId);
    }

    public boolean existsByItemAndData(Item item, String data) {
        return reservaRepository.existsByItemAndData(item, data);
    }
}
