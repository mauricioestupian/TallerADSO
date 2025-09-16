package com.taller1.taller1.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmpleadoCreateDTO {
    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    private String direccion;
    private String telefono;

    @NotNull
    private Byte idCargo;

    private Long idOficina;
}
