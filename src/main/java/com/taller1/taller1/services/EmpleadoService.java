package com.taller1.taller1.services;

import java.util.List;

import com.taller1.taller1.dtos.EmpleadoCreateDTO;
import com.taller1.taller1.dtos.EmpleadoDTO;
import com.taller1.taller1.dtos.EmpleadoUpdateDTO;

public interface EmpleadoService {
    EmpleadoDTO guardar(EmpleadoCreateDTO dto);

    EmpleadoDTO buscarPorId(Long id);

    List<EmpleadoDTO> listarTodos();

    void eliminar(Long id);

    EmpleadoDTO actualizarEmpleado(EmpleadoUpdateDTO dto);

}
