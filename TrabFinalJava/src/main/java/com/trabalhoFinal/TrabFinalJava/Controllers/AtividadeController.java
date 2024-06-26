package com.trabalhoFinal.TrabFinalJava.Controllers;


import com.trabalhoFinal.TrabFinalJava.Enums.ETipoUser;
import com.trabalhoFinal.TrabFinalJava.Models.Atividade;
import com.trabalhoFinal.TrabFinalJava.Models.Usuario;
import com.trabalhoFinal.TrabFinalJava.Services.AtividadeService;
import com.trabalhoFinal.TrabFinalJava.Services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/atividades")
    public String listarAtividades(Model model, HttpServletRequest request, HttpSession session) {
        List<Atividade> atividades = atividadeService.getAllAtividades();
        model.addAttribute("atividades", atividades);
        List<Usuario> instrutores = usuarioService.findByRole(ETipoUser.EMPREGADO);

        // Verificar o tipo de usuário logado
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario != null && (usuario.getRole().equals(ETipoUser.ADMIN) || usuario.getRole().equals(ETipoUser.EMPREGADO))) {
            model.addAttribute("isAdminOrEmpregado", true);
            model.addAttribute("instrutores", instrutores);
        } else {
            model.addAttribute("isAdminOrEmpregado", false);
        }

        session.setAttribute("instrutores", instrutores);
        return "atividades";
    }

    @PostMapping("/atividades/new")
    public String adicionarAtividade(@RequestParam String titulo,
                                     @RequestParam Long instrutorId,
                                     @RequestParam String dataHora,
                                     Model model) {
        Usuario instrutor = usuarioService.getUsuarioById(instrutorId);
        Atividade atividade = new Atividade();
        atividade.setTitulo(titulo);
        atividade.setInstrutor(instrutor);
        atividade.setDataHora(dataHora);

        atividadeService.saveAtividade(atividade);
        return "redirect:/atividades";
    }

//    @PostMapping("/atividades/inscrever")
//    public String inscreverAtividade(@RequestParam("atividadeId") Long atividadeId,
//                                     HttpServletRequest request,
//                                     RedirectAttributes redirectAttributes) {
//        Usuario currentUser = (Usuario) request.getSession().getAttribute("usuario");
//        Usuario usuario = usuarioService.findByUsername(currentUser.getUsername())
//                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
//
//        // Verifica se o usuário já está inscrito na atividade
//        if (atividadeService.usuarioEstaInscritoNaAtividade(atividadeId, usuario)) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Usuario ja esta inscrito");
//            return "redirect:/atividades?error=UsuarioJaInscrito";
//        }
//
//        atividadeService.inscreverUsuario(atividadeId, usuario);
//        redirectAttributes.addFlashAttribute("successMessage", "Inscrito com sucesso!");
//        return "redirect:/atividades";
//    }

}
