package com.taller1.taller1.services;

import java.util.List;

import com.taller1.taller1.dtos.EmpleadoDTO;

public interface EmpleadoService {
    EmpleadoDTO guardar(EmpleadoDTO dto);

    EmpleadoDTO buscarPorId(Long id);

    List<EmpleadoDTO> listarTodos();

    void eliminar(Long id);
}
