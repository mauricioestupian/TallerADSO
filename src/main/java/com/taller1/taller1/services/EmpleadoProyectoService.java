package com.taller1.taller1.services;

import java.util.List;

import com.taller1.taller1.dtos.EmpleadoAsignadoDTO;
import com.taller1.taller1.dtos.EmpleadoProyectoDTO;
import com.taller1.taller1.dtos.ProyectoAsignadoDTO;
import com.taller1.taller1.dtos.RespAsignacionMasivaDTO;
import com.taller1.taller1.dtos.ResultadoAsignacionDTO;

public interface EmpleadoProyectoService {
    EmpleadoProyectoDTO asignar(EmpleadoProyectoDTO dto);

    List<EmpleadoProyectoDTO> listarPorEmpleado(Long empleadoId);

    List<ProyectoAsignadoDTO> listarPorEmpleado2(Long empleadoId);

    List<EmpleadoProyectoDTO> listarPorProyecto(Long proyectoId);

    List<EmpleadoAsignadoDTO> listarPorProyecto2(Long proyectoId);

    void eliminarAsignacion(Long empleadoId, Long proyectoId);

    RespAsignacionMasivaDTO asignarEmpleados(List<EmpleadoProyectoDTO> asignaciones);

    List<ResultadoAsignacionDTO> asignarEmpleadosNormal(List<EmpleadoProyectoDTO> asignaciones);

    void desvincularEmpleado(Long empleadoId, Long proyectoId);

    List<ResultadoAsignacionDTO> desvincularAsignacionesMasivas(List<EmpleadoProyectoDTO> asignaciones);

    List<ResultadoAsignacionDTO> eliminarAsignacionesMasivas(List<EmpleadoProyectoDTO> asignaciones);

}
