package com.taller1.taller1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.taller1.taller1.repositoryes.EmpleadoRepository;
import com.taller1.taller1.services.JwtService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtService jwtService;
    private EmpleadoRepository empleadoRepo;

    public SecurityConfig(JwtService jwtService, EmpleadoRepository empleadoRepo) {
        this.jwtService = jwtService;
        this.empleadoRepo = empleadoRepo;
    }

    @Bean
    public SecurityFilterChain filtro(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/autenticacion/**",
                                "/api/autenticacion/iniciar-sesion",
                                "/api/cargos/**", // Teninedo en cuenta que los cargos no requieren autenficacion esta
                                                  // es
                                // la manera de rutas permitidas sin segurirdad
                                "/api/oficinas/**", // lo mismo pero como oficina contiene endpoints adicionales es
                                // necesario /**
                                "/api/cargos",
                                "/api/debug/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/api/empleados/**")
                        .permitAll()
                        // a continuacion se relacionan las urls privadas y roles con acce
                        .anyRequest().authenticated())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}