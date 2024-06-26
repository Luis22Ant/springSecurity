package com.trabalhoFinal.TrabFinalJava.Services;

import com.trabalhoFinal.TrabFinalJava.Enums.ETipoUser;
import com.trabalhoFinal.TrabFinalJava.Models.Usuario;
import com.trabalhoFinal.TrabFinalJava.Repository.UsuarioRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Getter
    Usuario usuario = new Usuario();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username);
        this.usuario = user;
        if(user != null){
            return new UserSecurityDetails(user);
        }
        return null;
    }

    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        System.out.println("Número de usuários encontrados: " + usuarios.size());
        for (Usuario usuario : usuarios) {
            System.out.println("Usuário encontrado: " + usuario.getUsername());
        }
        return usuarios;
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> findByUsernameContaining(String username) {
        return usuarioRepository.findByUsernameContaining(username);
    }

    public List<Usuario> findByRole(ETipoUser role) {
        List<Usuario> usuarios = usuarioRepository.findByRole(role);
        System.out.println("Número de usuários encontrados com role '" + role + "': " + usuarios.size());
        for (Usuario usuario : usuarios) {
            System.out.println("Usuário encontrado: " + usuario.getUsername() + ", Role: " + usuario.getRole());
        }
        return usuarios;
    }
}
