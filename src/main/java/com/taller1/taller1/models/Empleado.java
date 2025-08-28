package com.taller1.taller1.models;

import java.util.ArrayList;
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
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String ape;

    @Column(length = 60)
    private String dir;

    private Long tel;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cargoId", nullable = false, foreignKey = @ForeignKey(name = "FK_empleado_cargo"))
    private Cargo cargo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oficina_id", referencedColumnName = "id")
    private Oficina oficina;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmpleadoProyecto> proyectos = new ArrayList<>();

    /*
     * @ManyToMany(cascade = CascadeType.ALL)
     * 
     * @JoinTable(name = "empleado_proyecto", joinColumns = @JoinColumn(name =
     * "empleado_id"), inverseJoinColumns = @JoinColumn(name = "proyecto_id"))
     * private List<Proyecto> proyectos = new ArrayList<Proyecto>();
     */

}
