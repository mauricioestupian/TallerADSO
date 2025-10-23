package com.taller1.taller1.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.taller1.taller1.models.Empleado;
import com.taller1.taller1.repositoryes.EmpleadoRepository;
import com.taller1.taller1.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFiltroAutenticacion extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final EmpleadoRepository empleadoRepo;

    public JwtFiltroAutenticacion(JwtService jwtService, EmpleadoRepository empleadoRepo) {
        this.jwtService = jwtService;
        this.empleadoRepo = empleadoRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("Ejecutando JwtFiltroAutenticacion para: " + request.getRequestURI());

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = jwtService.obtenerUsuario(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Empleado empleado = empleadoRepo.findByUsername(username).orElse(null);

                if (empleado != null && jwtService.esTokenValido(token, empleado)) {
                    String rol = "ROLE_" + empleado.getCargo().getCargo().toUpperCase().replace(" ", "_");

                    // verificacion por consola del usuario que se loguea
                    System.out.println("Token recibido: " + token);
                    System.out.println("Usuario extraído: " + empleado.getUsername());
                    System.out.println("Empleado encontrado: " + (empleado != null));
                    System.out.println("Rol asignado: " + rol);
                    System.out.println("Token válido: " + jwtService.esTokenValido(token, empleado));
                    System.out.println("Contexto autenticado: "
                            + (SecurityContextHolder.getContext().getAuthentication() != null));

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            empleado, null, List.of(new SimpleGrantedAuthority(rol)));
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }

        // ✅ Siempre continúa con la cadena, sin bloquear
        filterChain.doFilter(request, response);
    }
}