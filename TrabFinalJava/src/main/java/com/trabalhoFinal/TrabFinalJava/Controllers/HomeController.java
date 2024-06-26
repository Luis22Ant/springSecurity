package com.trabalhoFinal.TrabFinalJava.Controllers;

import com.trabalhoFinal.TrabFinalJava.Models.Usuario;
import com.trabalhoFinal.TrabFinalJava.Services.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}