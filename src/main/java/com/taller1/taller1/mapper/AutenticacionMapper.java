package com.taller1.taller1.mapper;

import com.taller1.taller1.dtos.RespuestaLoginDTO;
import com.taller1.taller1.models.Empleado;

public interface AutenticacionMapper {
    RespuestaLoginDTO aRespuestaInicioSesionDTO(Empleado empleado, String token);
}
