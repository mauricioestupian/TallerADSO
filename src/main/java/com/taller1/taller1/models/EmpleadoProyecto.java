package com.taller1.taller1.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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

    @EmbeddedId
    private EmpleadoProyectoId id = new EmpleadoProyectoId();

    @ManyToOne
    @MapsId("empleadoId")
    @JoinColumn(name = "empleado_id", foreignKey = @ForeignKey(name = "FK_empleado_proyecto_empleado"))
    private Empleado empleado;

    @ManyToOne
    @MapsId("proyectoId")
    @JoinColumn(name = "proyecto_id")
    private Proyecto proyecto;

    @NotNull
    @Column(name = "fecha_asignacion")
    private LocalDate fechaAsignacion;

    @Size(max = 500)
    @Column(name = "observaciones")
    private String observaciones;
}