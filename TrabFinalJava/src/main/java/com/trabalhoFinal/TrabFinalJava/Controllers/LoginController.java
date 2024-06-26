package com.trabalhoFinal.TrabFinalJava.Controllers;

import com.trabalhoFinal.TrabFinalJava.Enums.ETipoUser;
import com.trabalhoFinal.TrabFinalJava.Models.Usuario;
import com.trabalhoFinal.TrabFinalJava.Services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

//    @Autowired
//    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
//
//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
//        Optional<Usuario> usuarioOptional = usuarioService.findByUsername(username);
//
//        if (usuarioOptional.isPresent()) {
//            Usuario usuario = usuarioOptional.get();
//
//            // Verifica se a senha fornecida corresponde à senha armazenada (não ideal, usar hash)
//            if (usuario.getPassword().equals(password)) {
//                session.setAttribute("usuario", usuario);
//
//                // Redireciona com base no papel do usuário
//                if (usuario.getRole() == ETipoUser.ADMIN || usuario.getRole() == ETipoUser.EMPREGADO) {
//                    List<Usuario> usuarios = usuarioService.getAllUsuarios();
//                    session.setAttribute("usuarios", usuarios);
//                    return "redirect:/homeAdmin";
//                } else {
//                    return "redirect:/home";
//                }
//            }
//        }
//
//        // Se as credenciais estiverem incorretas, retorna para a página de login com mensagem de erro
//        model.addAttribute("error", "Nome ou senha inválido!");
//        return "login";
//    }
//
//
//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        request.getSession().invalidate();
//
//        return "redirect:/login";
//    }
//}
