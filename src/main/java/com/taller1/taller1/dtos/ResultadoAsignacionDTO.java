package com.taller1.taller1.dtos;

import lombok.Data;

@Data
public class ResultadoAsignacionDTO {
    private Long empleadoId;
    private Long proyectoId;
    private String estado; // "ASIGNADO", "DUPLICADO", "ERROR_VALIDACION", etc.
    private String mensaje; // Detalle del resultado o error
}
