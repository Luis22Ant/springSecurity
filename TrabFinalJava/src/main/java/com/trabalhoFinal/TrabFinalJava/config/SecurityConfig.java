package com.trabalhoFinal.TrabFinalJava.config;

import com.trabalhoFinal.TrabFinalJava.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UsuarioService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService);


        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // Página de login personalizada
                        .defaultSuccessUrl("/home", true) // URL de sucesso após o login
                        .failureUrl("/login?error=true") // URL de falha no login
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL para efetuar logout
                        .logoutSuccessUrl("/login?logout") // URL de sucesso após o logout
                        .deleteCookies("JSESSIONID") // Remove cookies após o logout
                );

        return http.build();
    }
}
