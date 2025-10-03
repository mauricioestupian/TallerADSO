package com.taller1.taller1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.taller1.dtos.LoginDto;
import com.taller1.taller1.dtos.RespuestaLoginDTO;
import com.taller1.taller1.services.AutenticacionService;

@RestController
@RequestMapping("/api/autenticacion")
public class AutenticacionController {

    private final AutenticacionService servicio;

    public AutenticacionController(AutenticacionService servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<RespuestaLoginDTO> iniciarSesion(@RequestBody LoginDto solicitud) {
        return ResponseEntity.ok(servicio.iniciarSesion(solicitud));
    }
}
