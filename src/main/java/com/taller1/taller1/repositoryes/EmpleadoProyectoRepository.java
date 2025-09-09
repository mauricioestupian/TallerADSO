package com.taller1.taller1.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taller1.taller1.models.EmpleadoProyecto;
import com.taller1.taller1.models.EmpleadoProyectoId;

@Repository
public interface EmpleadoProyectoRepository extends JpaRepository<EmpleadoProyecto, EmpleadoProyectoId> {
    List<EmpleadoProyecto> findByEmpleado_Id(Long empleadoId);

    List<EmpleadoProyecto> findByProyecto_Id(Long proyectoId);
}

// -EmpleadoProyecto:
/*
 * es la entidad que representa la asignación entre un empleado y un proyecto.
 * 
 * -EmpleadoProyectoId: es la clave compuesta que identifica cada asignación
 * (empleado + proyecto).
 * 
 * - Al extender JpaRepository, heredas métodos
 * como save(), findById(), deleteById(), etc., sin escribirlos tú mismo.
 */