package com.taller1.taller1.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmpleadoCrearDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    private String direccion;

    private String telefono;

    @NotNull
    private Byte idCargo;

    @NotNull
    private Long idOficina;

    @NotBlank
    @Size(min = 4, max = 100)
    private String username;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
}
