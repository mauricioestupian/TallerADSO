package com.taller1.taller1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.taller1.taller1.dtos.LoginDto;
import com.taller1.taller1.dtos.RespuestaLoginDTO;
import com.taller1.taller1.models.Empleado;
import com.taller1.taller1.repositoryes.EmpleadoRepository;
import com.taller1.taller1.services.JwtService;

@RestController
@RequestMapping("/api/autenticacion")
public class AutenticacionController {

    private final EmpleadoRepository empleadoRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AutenticacionController(EmpleadoRepository empleadoRepo,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.empleadoRepo = empleadoRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        Empleado empleado = empleadoRepo.findByUsername(dto.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado"));

        if (!passwordEncoder.matches(dto.getPassword(), empleado.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }

        String token = jwtService.generarToken(empleado);

        RespuestaLoginDTO respuesta = new RespuestaLoginDTO();
        respuesta.setToken(token);
        respuesta.setNombreCompleto(empleado.getNom() + " " + empleado.getApe());
        respuesta.setRol(empleado.getCargo().getCargo());

        return ResponseEntity.ok(respuesta);

    }
}