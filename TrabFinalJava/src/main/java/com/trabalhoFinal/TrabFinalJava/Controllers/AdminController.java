package com.trabalhoFinal.TrabFinalJava.Controllers;

import com.trabalhoFinal.TrabFinalJava.Enums.ETipoUser;
import com.trabalhoFinal.TrabFinalJava.Models.Usuario;
import com.trabalhoFinal.TrabFinalJava.Services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.AttributedString;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

//    @GetMapping("/homeAdmin")
//    public String adminHome(@RequestParam(name = "search", required = false) String searchUsername,
//                            @RequestParam(name = "role", required = false) ETipoUser searchRole,
//                            HttpSession session, Model model) {
//        Usuario usuario = (Usuario) session.getAttribute("usuario");
//        if (usuario == null || !ETipoUser.ADMIN.equals(usuario.getRole())) {
//            return "redirect:/login";
//        }
//
//        List<Usuario> usuarios;
//        if (searchUsername != null && !searchUsername.isEmpty()) {
//            usuarios = usuarioService.findByUsernameContaining(searchUsername);
//        } else if (searchRole != null) {
//            usuarios = usuarioService.findByRole(searchRole);
//        } else {
//            usuarios = usuarioService.getAllUsuarios();
//        }
//
//        System.out.println("Número de usuários após filtro: " + usuarios.size());
//        model.addAttribute("usuarios", usuarios);
//        return "homeAdmin";
//    }

    @GetMapping("/homeAdmin")
    public String homeAdmin(){
        return "redirect:/homeAdmin";
    }


    @PostMapping("/adicionarUsuario")
    public String adicionarUsuario(@ModelAttribute Usuario usuario, HttpSession session, Model model) {
        usuarioService.saveUsuario(usuario);

        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        session.setAttribute("usuarios", usuarios);

        model.addAttribute("usuarios", usuarios);

        return "redirect:/homeAdmin";
    }


    @PostMapping("/editarUsuario")
    public String editarUsuario(@ModelAttribute Usuario usuario, HttpSession session, Model model) {
        
        usuarioService.saveUsuario(usuario);

        
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        session.setAttribute("usuarios", usuarios);

        
        model.addAttribute("usuarios", usuarios);

        
        return "redirect:/homeAdmin";
    }

    @GetMapping("/deletarUsuario/{id}")
    public String deletarUsuario(@PathVariable Long id, HttpSession session, Model model) {
        
        usuarioService.deleteUsuario(id);

        
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        session.setAttribute("usuarios", usuarios);

        
        model.addAttribute("usuarios", usuarios);

        
        return "redirect:/homeAdmin";
    }
}