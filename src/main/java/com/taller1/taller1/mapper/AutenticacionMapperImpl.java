package com.taller1.taller1.mapper;

import org.springframework.stereotype.Component;

import com.taller1.taller1.dtos.RespuestaLoginDTO;
import com.taller1.taller1.models.Empleado;

@Component
public class AutenticacionMapperImpl implements AutenticacionMapper {

    @Override
    public RespuestaLoginDTO aRespuestaInicioSesionDTO(Empleado empleado, String token) {
        RespuestaLoginDTO dto = new RespuestaLoginDTO();
        dto.setToken(token);
        dto.setNombreCompleto(empleado.getNom() + " " + empleado.getApe());
        dto.setRol(empleado.getCargo().getCargo());
        return dto;
    }
}
