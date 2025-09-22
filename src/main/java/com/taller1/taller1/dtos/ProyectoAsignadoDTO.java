package com.taller1.taller1.dtos;

import java.time.LocalDate;

import lombok.Data;

// DTO creado para representar proyectos asignados a un empleado
@Data
public class ProyectoAsignadoDTO {
    private String nombreProyecto;
    private LocalDate fechaAsignacion;
    private String observaciones;
}
