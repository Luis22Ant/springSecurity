package com.trabalhoFinal.TrabFinalJava.Repository;

import com.trabalhoFinal.TrabFinalJava.Enums.ETipoUser;
import com.trabalhoFinal.TrabFinalJava.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
//    Optional<Usuario> findByUsername(String username);
    Usuario findByUsername(String username);
    List<Usuario> findAll();
    List<Usuario> findByUsernameContaining(String username);
    List<Usuario> findByRole(ETipoUser role);
}
