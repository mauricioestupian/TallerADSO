package com.taller1.taller1.services;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.taller1.taller1.dtos.EmpleadoProyectoDTO;
import com.taller1.taller1.mapper.EmpleadoProyectoMapper;
import com.taller1.taller1.models.EmpleadoProyecto;
import com.taller1.taller1.models.EmpleadoProyectoId;

@Service
public class EmpleadoProyectoServiceImpl implements EmpleadoProyectoService {

    private final EmpleadoProyectoRepository repository;
    private final EmpleadoProyectoMapper mapper;

    public EmpleadoProyectoServiceImpl(EmpleadoProyectoRepository repository, EmpleadoProyectoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EmpleadoProyectoDTO asignar(EmpleadoProyectoDTO dto) {
        EmpleadoProyecto ep = mapper.toEntity(dto);
        EmpleadoProyecto guardado = repository.save(ep); // ← Aquí debe funcionar
        return mapper.toDTO(guardado);
    }

    @Override
    public List<EmpleadoProyectoDTO> listarPorEmpleado(Long empleadoId) {
        return repository.findByEmpleadoId(empleadoId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmpleadoProyectoDTO> listarPorProyecto(Long proyectoId) {
        return repository.findByProyectoId(proyectoId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarAsignacion(Long empleadoId, Long proyectoId) {
        EmpleadoProyectoId id = new EmpleadoProyectoId(empleadoId, proyectoId);
        repository.deleteById(id);
    }
}
