package com.taller1.taller1.mapper;

import org.springframework.stereotype.Component;

import com.taller1.taller1.dtos.ProyectoDTO;
import com.taller1.taller1.models.Proyecto;

@Component
public class ProyectoMapperImpl implements ProyectoMapper {

    @Override
    public Proyecto toProyecto(ProyectoDTO dto) {
        Proyecto proyecto = new Proyecto();
        proyecto.setId(dto.getId());
        proyecto.setNombre(dto.getNombre());
        proyecto.setPresupuesto(dto.getPresupuesto());
        proyecto.setFechaInicio(dto.getFechaInicio());
        proyecto.setFechaFin(dto.getFechaFin());
        return proyecto;
    }

    @Override
    public ProyectoDTO toProyectoDTO(Proyecto entity) {
        return new ProyectoDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getPresupuesto(),
                entity.getFechaInicio(),
                entity.getFechaFin());
    }

}
