package com.taller1.taller1.services;

import java.util.List;

import com.taller1.taller1.dtos.EmpleadoCrearDTO;
import com.taller1.taller1.dtos.EmpleadoDTO;
import com.taller1.taller1.dtos.EmpleadoUpdateDTO;

public interface EmpleadoService {
    List<EmpleadoDTO> listarTodos();

    EmpleadoDTO buscarPorId(Long id);

    EmpleadoDTO guardar(EmpleadoCrearDTO dto);

    void eliminar(Long id);

    EmpleadoDTO actualizarEmpleado(EmpleadoUpdateDTO dto);

}
