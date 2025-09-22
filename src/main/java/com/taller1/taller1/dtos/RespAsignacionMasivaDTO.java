package com.taller1.taller1.dtos;

import java.util.List;

import lombok.Data;

@Data
public class RespAsignacionMasivaDTO {
    private int totalSolicitudes;
    private int totalAsignadas;
    private int totalDuplicadas;
    private int totalErrores;
    private List<ResultadoAsignacionDTO> resultados;

}
