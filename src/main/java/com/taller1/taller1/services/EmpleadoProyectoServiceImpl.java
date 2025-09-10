package com.taller1.taller1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.taller1.taller1.dtos.EmpleadoProyectoDTO;
import com.taller1.taller1.mapper.EmpleadoProyectoMapper;
import com.taller1.taller1.models.EmpleadoProyecto;
import com.taller1.taller1.models.EmpleadoProyectoId;
import com.taller1.taller1.repositoryes.EmpleadoProyectoRepository;

import jakarta.persistence.EntityNotFoundException;

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

        // Validación opcional: evitar duplicados
        EmpleadoProyectoId id = new EmpleadoProyectoId(dto.getEmpleadoId(), dto.getProyectoId());
        if (repository.existsById(id)) {
            throw new IllegalStateException("Ya existe una asignación para ese empleado y proyecto");
        }

        EmpleadoProyecto guardado = repository.save(ep);
        return mapper.toDTO(guardado);
    }

    @Override
    public List<EmpleadoProyectoDTO> listarPorEmpleado(Long empleadoId) {
        return repository.findByEmpleado_Id(empleadoId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmpleadoProyectoDTO> listarPorProyecto(Long proyectoId) {
        return repository.findByProyecto_Id(proyectoId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarAsignacion(Long empleadoId, Long proyectoId) {
        EmpleadoProyectoId id = new EmpleadoProyectoId(empleadoId, proyectoId);
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Asignación no encontrada");
        }
        repository.deleteById(id);
    }

    @Override
    public List<EmpleadoProyectoDTO> asignarEmpleados(List<EmpleadoProyectoDTO> lista) {
        List<EmpleadoProyectoDTO> empleado = new ArrayList<>();

        for (EmpleadoProyectoDTO dto : lista) {
            try {
                EmpleadoProyectoDTO asignado = asignar(dto); // Usa el método existente
                empleado.add(asignado);
            } catch (Exception e) {
                // Puedes registrar errores por DTO o devolver un resumen
                System.out.println("Error al asignar empleado " + dto.getEmpleadoId() + ": " + e.getMessage());
            }
        }

        return empleado;
    }

}