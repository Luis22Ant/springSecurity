package com.trabalhoFinal.TrabFinalJava.Services;

import com.trabalhoFinal.TrabFinalJava.Models.Atividade;
import com.trabalhoFinal.TrabFinalJava.Models.InscricaoUsuarioAtividade;
import com.trabalhoFinal.TrabFinalJava.Models.Usuario;
import com.trabalhoFinal.TrabFinalJava.Repository.AtividadeRepository;
import com.trabalhoFinal.TrabFinalJava.Repository.InscricaoUsuarioAtividadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AtividadeService {

    @Autowired
    private InscricaoUsuarioAtividadeRepository inscricaoUsuarioAtividadeRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    public List<Atividade> getAllAtividades() {
        return atividadeRepository.findAll();
    }

    public Atividade saveAtividade(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public Atividade getAtividadeById(Long id) {
        return atividadeRepository.findById(id).orElse(null);
    }

    public void deleteAtividade(Long id) {
        atividadeRepository.deleteById(id);
    }

    @Transactional
    public void inscreverUsuario(Long atividadeId, Usuario usuario) {
        // Verifica se o usuário já está inscrito na atividade
        if (usuarioEstaInscritoNaAtividade(atividadeId, usuario)) {
            throw new IllegalStateException("Usuário já está inscrito nesta atividade");
        }

        // Realiza a inscrição
        InscricaoUsuarioAtividade inscricao = new InscricaoUsuarioAtividade();
        inscricao.setUsuario(usuario);
        // Setar a atividade com base no ID fornecido
        Atividade atividade = atividadeRepository.findById(atividadeId)
                .orElseThrow(() -> new NoSuchElementException("Atividade não encontrada"));
        inscricao.setAtividade(atividade);

        inscricaoUsuarioAtividadeRepository.save(inscricao);
    }

    public boolean usuarioEstaInscritoNaAtividade(Long atividadeId, Usuario usuario) {
        return inscricaoUsuarioAtividadeRepository.existsByAtividadeIdAndUsuarioId(atividadeId, usuario.getId());
    }
}
