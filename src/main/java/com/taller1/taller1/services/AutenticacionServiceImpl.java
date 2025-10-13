package com.taller1.taller1.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taller1.taller1.dtos.LoginDto;
import com.taller1.taller1.dtos.RespuestaLoginDTO;
import com.taller1.taller1.exception.CredencialesInvalidasException;
import com.taller1.taller1.mapper.AutenticacionMapper;
import com.taller1.taller1.models.Empleado;
import com.taller1.taller1.repositoryes.EmpleadoRepository;

@Service
public class AutenticacionServiceImpl implements AutenticacionService {

    private final EmpleadoRepository empleadoRepo;
    private final JwtService jwtService;
    private final PasswordEncoder codificador;
    private final AutenticacionMapper autenticacionMapper;

    public AutenticacionServiceImpl(
            EmpleadoRepository empleadoRepo,
            JwtService jwtService,
            PasswordEncoder codificador,
            AutenticacionMapper autenticacionMapper) {
        this.empleadoRepo = empleadoRepo;
        this.jwtService = jwtService;
        this.codificador = codificador;
        this.autenticacionMapper = autenticacionMapper;
    }

    @Override
    public RespuestaLoginDTO iniciarSesion(LoginDto solicitud) {
        Empleado empleado = empleadoRepo.findByUsername(solicitud.getUsername())
                .orElseThrow(() -> new CredencialesInvalidasException("Usuario no encontrado"));

        if (!codificador.matches(solicitud.getPassword(), empleado.getPassword())) {
            throw new CredencialesInvalidasException("Clave incorrecta");
        }

        String token = jwtService.generarToken(empleado);
        return autenticacionMapper.aRespuestaInicioSesionDTO(empleado, token);
    }
}