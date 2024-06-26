package com.trabalhoFinal.TrabFinalJava.Repository;

import com.trabalhoFinal.TrabFinalJava.Models.InscricaoUsuarioAtividade;
import com.trabalhoFinal.TrabFinalJava.Models.Item;
import com.trabalhoFinal.TrabFinalJava.Models.Reserva;
import com.trabalhoFinal.TrabFinalJava.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InscricaoUsuarioAtividadeRepository extends JpaRepository<InscricaoUsuarioAtividade, Long> {
    List<InscricaoUsuarioAtividade> findAll();

    Optional<InscricaoUsuarioAtividade> findById(Long id);

    InscricaoUsuarioAtividade save(InscricaoUsuarioAtividade inscricaoUsuarioAtividade);

    void deleteById(Long id);

    boolean existsByAtividadeIdAndUsuarioId(Long atividadeId, Long usuarioId);
    List<InscricaoUsuarioAtividade> findByUsuario(Usuario usuario);

}
