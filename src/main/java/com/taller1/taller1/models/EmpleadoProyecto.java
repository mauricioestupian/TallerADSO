package com.taller1.taller1.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class EmpleadoProyecto {

    @Embeddable
    public class EmpleadoProyectoId implements Serializable {
        private Long empleadoId;
        private Long proyectoId;

        // equals, hashCode
    }

    @EmbeddedId
    private EmpleadoProyectoId id = new EmpleadoProyectoId();

    @ManyToOne
    @MapsId("empleadoId")
    private Empleado empleado;

    @ManyToOne
    @MapsId("proyectoId")
    private Proyecto proyecto;
    // Getters, setters, etc.
}
