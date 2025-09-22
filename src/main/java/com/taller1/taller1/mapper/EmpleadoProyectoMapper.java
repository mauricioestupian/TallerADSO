package com.taller1.taller1.mapper;

import com.taller1.taller1.dtos.EmpleadoAsignadoDTO;
import com.taller1.taller1.dtos.EmpleadoProyectoDTO;
import com.taller1.taller1.dtos.ProyectoAsignadoDTO;
import com.taller1.taller1.models.EmpleadoProyecto;

public interface EmpleadoProyectoMapper {

    EmpleadoProyecto toEmpleadoProyecto(EmpleadoProyectoDTO dto);

    EmpleadoProyectoDTO toDTO(EmpleadoProyecto empleadoProyecto);

    ProyectoAsignadoDTO toProyectoAsignadoDTO(EmpleadoProyecto entity);

    EmpleadoAsignadoDTO toEmpleadoAsignadoDTO(EmpleadoProyecto entity);

}
