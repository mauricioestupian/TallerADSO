package com.taller1.taller1.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String nombre;

    @NotNull
    @Email
    private String correo;

    @NotNull
    private String nombreCargo;

    @NotNull
    private String nombreOficina;

    // Esto en el caso de que quieras incluir las relaciones como DTOs anidados y en
    // la consulta se haga el mapeo correspondiente.
    /*
     * @NotNull(message = "La oficina es obligatoria")
     * private OficinaDTO oficina;
     * 
     * @NotNull(message = "El cargo es obligatorio")
     * private CargoDTO cargo;
     * 
     * private List<EmpleadoProyectoDTO> proyectos;
     * 
     */
    // Para esto es necesario que crees las clases OficinaDTO y CargoDTO si decides
    // incluirlas.
    /*
     * import lombok.Data;
     * 
     * @Data
     * public class OficinaDTO {
     * private Long id;
     * private String nombre;
     * private String ubicacion;
     * }
     * 
     * import lombok.Data;
     * 
     * @Data
     * public class CargoDTO {
     * private Long id;
     * private String nombre;
     * private String descripcion;
     * }
     */
}
