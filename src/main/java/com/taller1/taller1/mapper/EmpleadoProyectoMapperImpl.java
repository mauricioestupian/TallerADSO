package com.taller1.taller1.mapper;

import org.springframework.stereotype.Component;

import com.taller1.taller1.dtos.EmpleadoAsignadoDTO;
import com.taller1.taller1.dtos.EmpleadoProyectoDTO;
import com.taller1.taller1.dtos.ProyectoAsignadoDTO;
import com.taller1.taller1.models.Empleado;
import com.taller1.taller1.models.EmpleadoProyecto;
import com.taller1.taller1.models.EmpleadoProyectoId;
import com.taller1.taller1.models.Proyecto;
import com.taller1.taller1.repositoryes.EmpleadoRepository;
import com.taller1.taller1.repositoryes.ProyectoRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class EmpleadoProyectoMapperImpl implements EmpleadoProyectoMapper {

    private final EmpleadoRepository empleadoRepository;
    private final ProyectoRepository proyectoRepository;

    public EmpleadoProyectoMapperImpl(EmpleadoRepository empleadoRepository, ProyectoRepository proyectoRepository) {
        this.empleadoRepository = empleadoRepository;
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public EmpleadoProyecto toEmpleadoProyecto(EmpleadoProyectoDTO dto) {
        Empleado empleado = empleadoRepository.findById(dto.getEmpleadoId())
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));

        Proyecto proyecto = proyectoRepository.findById(dto.getProyectoId())
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado"));

        EmpleadoProyectoId id = new EmpleadoProyectoId(dto.getEmpleadoId(), dto.getProyectoId());

        EmpleadoProyecto ep = new EmpleadoProyecto();
        ep.setId(id);
        ep.setEmpleado(empleado);
        ep.setProyecto(proyecto);
        ep.setFechaAsignacion(dto.getFechaAsignacion());
        ep.setObservaciones(dto.getObservaciones());

        return ep;
    }

    @Override
    public EmpleadoProyectoDTO toDTO(EmpleadoProyecto entity) {
        return new EmpleadoProyectoDTO(
                entity.getEmpleado().getId(),
                entity.getEmpleado().getNom() + " " + entity.getEmpleado().getApe(),
                entity.getProyecto().getId(),
                entity.getProyecto().getNombre(),
                entity.getFechaAsignacion(),
                entity.getEstado(),
                entity.getObservaciones(),
                entity.getEmpleado().getUsername(),
                entity.getEmpleado().getPassword());
    }

    @Override
    public ProyectoAsignadoDTO toProyectoAsignadoDTO(EmpleadoProyecto entity) {
        ProyectoAsignadoDTO dto = new ProyectoAsignadoDTO();
        dto.setNombreProyecto(entity.getProyecto().getNombre());
        dto.setFechaAsignacion(entity.getFechaAsignacion());
        dto.setObservaciones(entity.getObservaciones());
        dto.setEstado(entity.getEstado());
        return dto;
    }

    @Override
    public EmpleadoAsignadoDTO toEmpleadoAsignadoDTO(EmpleadoProyecto entity) {
        EmpleadoAsignadoDTO dto = new EmpleadoAsignadoDTO();
        dto.setEmpleadoId(entity.getEmpleado().getId());
        dto.setNombreEmpleado(entity.getEmpleado().getNom());
        dto.setFechaAsignacion(entity.getFechaAsignacion());
        dto.setObservaciones(entity.getObservaciones());
        dto.setEstado(entity.getEstado());
        return dto;
    }

}
