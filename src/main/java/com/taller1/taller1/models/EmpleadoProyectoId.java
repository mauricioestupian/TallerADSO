package com.taller1.taller1.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data // Genera getters, setters, toString, equals y hashCode automáticamente
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoProyectoId implements Serializable {

    private Long empleadoId;
    private Long proyectoId;

    // Si no hicieramos uso de Lombok, deberiamos implementar equals y hashcode
    // para que JPA pueda comparar correctamente las claves compuestas
    // y manejar las entidades de manera adecuada
    // Lombok genera estos metodos automaticamente
    /*
     * @Override
     * public boolean equals(Object o) {
     * if (this == o)
     * return true;
     * if (!(o instanceof EmpleadoProyectoId))
     * return false;
     * EmpleadoProyectoId that = (EmpleadoProyectoId) o;
     * return Objects.equals(empleadoId, that.empleadoId) &&
     * Objects.equals(proyectoId, that.proyectoId);
     * }
     * 
     * @Override
     * public int hashCode() {
     * return Objects.hash(empleadoId, proyectoId);
     * }
     */
}
