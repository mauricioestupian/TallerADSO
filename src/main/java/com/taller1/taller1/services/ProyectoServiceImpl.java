package com.taller1.taller1.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.taller1.taller1.dtos.ProyectoDTO;
import com.taller1.taller1.mapper.ProyectoMapper;
import com.taller1.taller1.models.Proyecto;
import com.taller1.taller1.repositoryes.ProyectoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final ProyectoMapper proyectoMapper;

    public ProyectoServiceImpl(ProyectoRepository proyectoRepository, ProyectoMapper proyectoMapper) {
        this.proyectoRepository = proyectoRepository;
        this.proyectoMapper = proyectoMapper;
    }

    @Override
    public ProyectoDTO guardar(ProyectoDTO dto) {
        Proyecto proyecto = proyectoMapper.toProyecto(dto);
        Proyecto guardado = proyectoRepository.save(proyecto);
        return proyectoMapper.toProyectoDTO(guardado);
    }

    @Override
    public ProyectoDTO buscarPorId(Long id) {
        return proyectoRepository.findById(id)
                .map(proyectoMapper::toProyectoDTO)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado"));
    }

    @Override
    public List<ProyectoDTO> listarTodos() {
        return proyectoRepository.findAll()
                .stream()
                .map(proyectoMapper::toProyectoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto con ID " + id + " no encontrado"));

        proyectoRepository.delete(proyecto);
    }

    @Override
    public ProyectoDTO actualizar(Long id, ProyectoDTO dto) {
        // Paso 1: verificar existencia
        Proyecto existente = proyectoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado"));

        // Paso 2: validar fechas del DTO
        validarFechas(dto);

        // Paso 3: aplicar cambios
        existente.setNombre(dto.getNombre());
        existente.setPresupuesto(dto.getPresupuesto());
        existente.setFechaInicio(dto.getFechaInicio());
        existente.setFechaFin(dto.getFechaFin());

        Proyecto actualizado = proyectoRepository.save(existente);
        return proyectoMapper.toProyectoDTO(actualizado);
    }

    // Validación de fechas de inicio y fin
    private void validarFechas(ProyectoDTO dto) {
        if (dto.getFechaFin() != null && dto.getFechaFin().isBefore(dto.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
    }

}
