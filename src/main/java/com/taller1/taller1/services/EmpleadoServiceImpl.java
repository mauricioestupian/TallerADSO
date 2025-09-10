package com.taller1.taller1.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.taller1.taller1.dtos.EmpleadoDTO;
import com.taller1.taller1.mapper.EmpleadoMapper;
import com.taller1.taller1.models.Empleado;
import com.taller1.taller1.repositoryes.EmpleadoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    @Override
    public EmpleadoDTO guardar(EmpleadoDTO dto) {
        Empleado empleado = empleadoMapper.toEntity(dto);
        Empleado guardado = empleadoRepository.save(empleado);
        return empleadoMapper.toDTO(guardado);
    }

    @Override
    public EmpleadoDTO buscarPorId(Long id) {
        return empleadoRepository.findById(id)
                .map(empleadoMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
    }

    @Override
    public List<EmpleadoDTO> listarTodos() {
        return empleadoRepository.findAll()
                .stream()
                .map(empleadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }
}
