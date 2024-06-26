package com.trabalhoFinal.TrabFinalJava.Services;

import com.trabalhoFinal.TrabFinalJava.Models.Usuario;
import com.trabalhoFinal.TrabFinalJava.Enums.ETipoUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

public class UserSecurityDetails implements UserDetails {

    private Usuario user;

    public UserSecurityDetails(Usuario user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.user.getRole() == ETipoUser.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"),
                    new SimpleGrantedAuthority("ROLE_EMPREGADO"),
                    new SimpleGrantedAuthority("ROLE_USUARIO"));
        } else if (this.user.getRole() == ETipoUser.EMPREGADO) {
            return List.of(new SimpleGrantedAuthority("ROLE_EMPREGADO"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
        }
    }

    public Usuario getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return new BCryptPasswordEncoder().encode(user.getPassword());
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getTipoUserString() {
        return user.getRole().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}