package com.taller1.taller1.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller1.taller1.dtos.EmpleadoCreateDTO;
import com.taller1.taller1.dtos.EmpleadoDTO;
import com.taller1.taller1.dtos.EmpleadoUpdateDTO;
import com.taller1.taller1.mapper.EmpleadoMapper;
import com.taller1.taller1.models.Cargo;
import com.taller1.taller1.models.Empleado;
import com.taller1.taller1.models.EmpleadoProyecto;
import com.taller1.taller1.models.Oficina;
import com.taller1.taller1.repositoryes.CargoRepository;
import com.taller1.taller1.repositoryes.EmpleadoProyectoRepository;
import com.taller1.taller1.repositoryes.EmpleadoRepository;
import com.taller1.taller1.repositoryes.OficinaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoProyectoRepository empleadoProyectoRepository;
    private final EmpleadoMapper empleadoMapper;
    private final CargoRepository cargoRepository;
    private final OficinaRepository oficinaRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository,
            EmpleadoProyectoRepository empleadoProyectoRepository,
            EmpleadoMapper empleadoMapper, CargoRepository cargoRepository, OficinaRepository oficinaRepository) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoProyectoRepository = empleadoProyectoRepository;
        this.empleadoMapper = empleadoMapper;
        this.cargoRepository = cargoRepository;
        this.oficinaRepository = oficinaRepository;

    }

    @Override
    @Transactional
    public EmpleadoDTO guardar(EmpleadoCreateDTO dto) {
        Empleado empleado = empleadoMapper.toEmpleado(dto);
        Empleado guardado = empleadoRepository.save(empleado);
        return empleadoMapper.toDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public EmpleadoDTO buscarPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
        return empleadoMapper.toDTO(empleado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoDTO> listarTodos() {
        return empleadoRepository.findAll().stream()
                .map(empleadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));

        // Eliminar asignaciones primero
        List<EmpleadoProyecto> asignaciones = empleadoProyectoRepository.findByEmpleado(empleado);
        if (!asignaciones.isEmpty()) {
            empleadoProyectoRepository.deleteAll(asignaciones);
        }

        empleadoRepository.delete(empleado);
    }

    @Override
    @Transactional
    public EmpleadoDTO actualizarEmpleado(EmpleadoUpdateDTO dto) {
        Empleado empleado = empleadoRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));

        empleado.setNom(dto.getNombre());
        empleado.setApe(dto.getApellido());
        empleado.setDir(dto.getDireccion());
        empleado.setTel(dto.getTelefono());

        Cargo cargo = cargoRepository.findById(dto.getIdCargo())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrado"));
        empleado.setCargo(cargo);

        if (dto.getIdOficina() != null) {
            Oficina oficina = oficinaRepository.findById(dto.getIdOficina())
                    .orElseThrow(() -> new EntityNotFoundException("Oficina no encontrada"));
            empleado.setOficina(oficina);
        } else {
            empleado.setOficina(null);
        }

        Empleado actualizado = empleadoRepository.save(empleado);
        return empleadoMapper.toDTO(actualizado);
    }

}