package com.taller1.taller1.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoProyectoDTO {

    @NotNull(message = "El ID del empleado no puede ser nulo")
    private Long empleadoId;
    private String nombreEmpleado;// para leer el nombre del empleado

    @NotNull(message = "El ID del proyecto no puede ser nulo")
    private Long proyectoId;
    private String nombreProyecto; // para leer el nombre del proyecto

    @NotNull(message = "La fecha de asignación es obligatoria")
    private LocalDate fechaAsignacion;

    @Size(max = 500, message = "Las observaciones no pueden superar los 500 caracteres")
    private String observaciones;

    // Getters y setters con lombok
}
