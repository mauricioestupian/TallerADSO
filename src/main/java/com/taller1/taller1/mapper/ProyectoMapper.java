package com.taller1.taller1.mapper;

import com.taller1.taller1.dtos.ProyectoDTO;
import com.taller1.taller1.models.Proyecto;

public interface ProyectoMapper {

    Proyecto toProyecto(ProyectoDTO dto);

    ProyectoDTO toProyectoDTO(Proyecto proyecto);

}
