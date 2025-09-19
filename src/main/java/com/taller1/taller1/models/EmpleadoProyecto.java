package com.taller1.taller1.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleado_proyecto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoProyecto {

    /**
     * Clave compuesta que contiene los IDs de Empleado y Proyecto.
     * Se define como una clase embebida con @Embeddable.
     */
    @EmbeddedId
    private EmpleadoProyectoId id = new EmpleadoProyectoId();

    /**
     * Relación con Empleado.
     * - @MapsId indica que el campo empleadoId en la clave compuesta se mapea desde
     * esta relación.
     * - fetch = LAZY para evitar cargar el empleado completo si no es necesario.
     * - Es importante que el Empleado esté gestionado por JPA (recuperado desde el
     * repositorio).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empleadoId")
    @JoinColumn(name = "empleado_id", foreignKey = @ForeignKey(name = "FK_empleado_proyecto_empleado"))
    private Empleado empleado;

    /**
     * Relación con Proyecto.
     * - También se mapea desde la clave compuesta.
     * - Se recomienda que el Proyecto esté gestionado por JPA.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("proyectoId")
    @JoinColumn(name = "proyecto_id", foreignKey = @ForeignKey(name = "FK_empleado_proyecto_proyecto"))
    private Proyecto proyecto;

    /**
     * Fecha en que se asignó el empleado al proyecto.
     * - Campo obligatorio.
     */
    @NotNull
    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion;

    /**
     * Comentarios opcionales sobre la asignación.
     * - Máximo 500 caracteres.
     */
    @Size(max = 500)
    @Column(name = "observaciones")
    private String observaciones;
}