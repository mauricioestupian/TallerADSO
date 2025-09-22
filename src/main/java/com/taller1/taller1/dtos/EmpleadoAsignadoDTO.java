package com.taller1.taller1.dtos;

import java.time.LocalDate;

import lombok.Data;

// DTO creado para representar empleados asignados a un proyecto
@Data
public class EmpleadoAsignadoDTO {
    private Long empleadoId;
    private String nombreEmpleado;
    private LocalDate fechaAsignacion;
    private String observaciones;
}
