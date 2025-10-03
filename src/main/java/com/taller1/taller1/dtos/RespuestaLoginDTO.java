package com.taller1.taller1.dtos;

import lombok.Data;

@Data
public class RespuestaLoginDTO {
    private String token;
    private String nombreCompleto;
    private String rol;
}
