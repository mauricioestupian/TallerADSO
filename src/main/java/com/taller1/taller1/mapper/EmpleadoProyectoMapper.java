package com.taller1.taller1.mapper;

import com.taller1.taller1.dtos.EmpleadoProyectoDTO;
import com.taller1.taller1.models.EmpleadoProyecto;

public interface EmpleadoProyectoMapper {

    EmpleadoProyecto toEntity(EmpleadoProyectoDTO dto);

    EmpleadoProyectoDTO toDTO(EmpleadoProyecto entity);

}
