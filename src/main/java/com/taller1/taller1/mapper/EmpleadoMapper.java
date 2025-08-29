package com.taller1.taller1.mapper;

import com.taller1.taller1.dtos.EmpleadoDTO;
import com.taller1.taller1.models.Empleado;

public interface EmpleadoMapper {

    Empleado toEntity(EmpleadoDTO dto);

    EmpleadoDTO toDTO(Empleado entity);

}
