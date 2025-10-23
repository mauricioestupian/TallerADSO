package com.taller1.taller1.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeguridadDebugController {

    @GetMapping("/api/debug/seguridad")
    public String verificarSeguridad() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            return "No hay autenticación en el contexto.";
        }

        String usuario = auth.getName();
        String rol = auth.getAuthorities().toString();

        return "Usuario autenticado: " + usuario + "\nRol asignado: " + rol;
    }
}
