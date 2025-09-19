package com.taller1.taller1.repositoryes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taller1.taller1.models.Oficina;

public interface OficinaRepository extends JpaRepository<Oficina, Long> {

    // Devuelve oficinas sin empleado asignado
    List<Oficina> findByEmpleadoIsNull();

    // Alternativa usando JPQL para ofurnas sin empleado asignado
    @Query("SELECT o FROM Oficina o LEFT JOIN o.empleado e WHERE e IS NULL")
    List<Oficina> findOficinasSinEmpleado();

    /*
     * o es el alias de la entidad Oficina.
     * empleado es una propiedad dentro de Oficina, que representa una
     * relación @OneToOne con la entidad Empleado.
     */

}
