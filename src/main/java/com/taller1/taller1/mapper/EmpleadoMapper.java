package com.taller1.taller1.mapper;

import com.taller1.taller1.dtos.EmpleadoCreateDTO;
import com.taller1.taller1.dtos.EmpleadoDTO;
import com.taller1.taller1.models.Empleado;

public interface EmpleadoMapper {

    Empleado toEmpleado(EmpleadoCreateDTO dto);

    EmpleadoDTO toDTO(Empleado empleado);

}
