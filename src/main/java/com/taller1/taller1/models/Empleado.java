package com.taller1.taller1.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String ape;

    @Column(length = 60)
    private String dir;

    private String tel;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cargo_id", nullable = false, foreignKey = @ForeignKey(name = "FK_empleado_cargo"))
    private Cargo cargo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oficina_id", referencedColumnName = "id")
    private Oficina oficina;

    // un Empleado puede estar en varios proyectos es decir tener muchas
    // asignaciones
    @OneToMany(mappedBy = "empleado")
    private List<EmpleadoProyecto> asignaciones;// podemos ver los proyectos a los que esta asignado un empleado

}
