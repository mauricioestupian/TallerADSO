package com.taller1.taller1.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taller1.taller1.models.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

}
