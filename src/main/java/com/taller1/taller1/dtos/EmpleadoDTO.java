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

    @NotNull(message = "El ID del empleado es obligatorio")
    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotNull(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    private String correo;

    @NotNull(message = "El nombre del cargo es obligatorio")
    private String nombreCargo;

    @NotNull(message = "El nombre de la oficina es obligatorio")
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
