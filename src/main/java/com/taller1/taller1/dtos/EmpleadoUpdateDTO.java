package com.taller1.taller1.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmpleadoUpdateDTO {
    @NotNull
    private Long id;

    private String nombre;

    @NotBlank
    private String apellido;

    private String direccion;
    private String telefono;

    @NotNull
    private Byte idCargo;

    private Long idOficina;
}
