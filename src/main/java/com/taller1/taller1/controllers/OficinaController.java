package com.taller1.taller1.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.taller1.dtos.OficinaDTO;
import com.taller1.taller1.repositoryes.OficinaRepository;

@RestController
@RequestMapping("/api/oficinas")
public class OficinaController {

    @Autowired
    private OficinaRepository oficinaRepo;

    @GetMapping
    public List<OficinaDTO> obtenerSoloOficinas() {
        return oficinaRepo.findAll().stream().map(oficina -> {
            OficinaDTO dto = new OficinaDTO();
            dto.setId(oficina.getId());
            dto.setNombre(oficina.getNombre());
            dto.setUbicacion(oficina.getUbicacion());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/noasignadas")
    public List<OficinaDTO> oficinasSinEmpleado() {
        return oficinaRepo.findByEmpleadoIsNull().stream().map(oficina -> {
            OficinaDTO dto = new OficinaDTO();
            dto.setId(oficina.getId());
            dto.setNombre(oficina.getNombre());
            dto.setUbicacion(oficina.getUbicacion());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/oficinasinempleado")
    public List<OficinaDTO> oficinasSinEmpleadoJPQL() {
        return oficinaRepo.findOficinasSinEmpleado().stream().map(oficina -> {
            OficinaDTO dto = new OficinaDTO();
            dto.setId(oficina.getId());
            dto.setNombre(oficina.getNombre());
            dto.setUbicacion(oficina.getUbicacion());
            return dto;
        }).collect(Collectors.toList());
    }

}
