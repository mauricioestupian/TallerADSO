package com.taller1.taller1.services;

import java.util.List;

import com.taller1.taller1.dtos.ProyectoDTO;

public interface ProyectoService {
    ProyectoDTO guardar(ProyectoDTO dto);

    ProyectoDTO buscarPorId(Long id);

    List<ProyectoDTO> listarTodos();

    void eliminar(Long id);

}
