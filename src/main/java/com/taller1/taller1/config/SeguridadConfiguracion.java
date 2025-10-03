package com.taller1.taller1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.taller1.taller1.repositoryes.EmpleadoRepository;
import com.taller1.taller1.services.JwtService;

@Configuration
@EnableWebSecurity
public class SeguridadConfiguracion {

    private final JwtService jwtService;
    private final EmpleadoRepository empleadoRepo;

    public SeguridadConfiguracion(JwtService jwtService, EmpleadoRepository empleadoRepo) {
        this.jwtService = jwtService;
        this.empleadoRepo = empleadoRepo;
    }

    @Bean
    public SecurityFilterChain filtro(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/autenticacion/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtFiltroAutenticacion(jwtService, empleadoRepo),
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder codificador() {
        return new BCryptPasswordEncoder();
    }
}