package com.taller1.taller1.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "empleadoId", "proyectoId"
})
public class EmpleadoProyectoId implements Serializable {
    public EmpleadoProyectoId(Integer id, Integer id2) {
    }

    private Long empleadoId;
    private Long proyectoId;
}

/*
 * terminos a estudiar
 * Serializable
 * 
 * @EqualsAndHashCode = equals y hashCode
 * 
 * @EmbeddedId = - Uso de claves compuestas
 * 
 * @MapsId
 * 
 * Uso de Entidad intermedia enriquecida??
 */
