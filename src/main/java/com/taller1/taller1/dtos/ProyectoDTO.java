package com.taller1.taller1.dtos;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoDTO {

    @NotNull(message = "El ID del proyecto es obligatorio")
    private Long id;

    @NotNull(message = "El nombre del proyecto es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción no debe superar los 500 caracteres")
    private String descripcion;

    // Opcional: lista de empleados asignados
    private List<EmpleadoProyectoDTO> empleados;
}
