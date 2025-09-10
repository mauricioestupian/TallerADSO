package com.taller1.taller1.services;

import java.util.List;

import com.taller1.taller1.dtos.EmpleadoProyectoDTO;

public interface EmpleadoProyectoService {
    EmpleadoProyectoDTO asignar(EmpleadoProyectoDTO dto);

    List<EmpleadoProyectoDTO> listarPorEmpleado(Long empleadoId);

    List<EmpleadoProyectoDTO> listarPorProyecto(Long proyectoId);

    void eliminarAsignacion(Long empleadoId, Long proyectoId);

    List<EmpleadoProyectoDTO> asignarEmpleados(List<EmpleadoProyectoDTO> dtos);
}
