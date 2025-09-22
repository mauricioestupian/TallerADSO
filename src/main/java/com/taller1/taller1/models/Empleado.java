package com.taller1.taller1.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "empleado",
        // Define una restricción de unicidad en la columna oficina_id para asegurar que
        // se mantenga la relación uno a uno entre Empleado y Oficina, adicionalmente al
        // mapeo con @OneToOne le asignamos un nombre al constraint para facilitar su
        // identificación en la base de datos. poder usar la restriccion para respuestas
        // en el controlador
        uniqueConstraints = {
                @UniqueConstraint(name = "Cosnt_empleado_oficina", columnNames = { "oficina_id" })
        })
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleado_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String ape;

    @Column(length = 60)
    private String dir;

    private String tel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id", nullable = false, foreignKey = @ForeignKey(name = "FK_empleado_cargo"))
    private Cargo cargo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oficina_id", foreignKey = @ForeignKey(name = "FK_empleado_oficina"))
    private Oficina oficina;

    // un Empleado puede estar en varios proyectos es decir tener muchas
    // asignaciones

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmpleadoProyecto> asignaciones = new ArrayList<>();

}
