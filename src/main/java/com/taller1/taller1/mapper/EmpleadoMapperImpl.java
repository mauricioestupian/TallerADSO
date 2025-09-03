package com.taller1.taller1.mapper;

import org.springframework.stereotype.Component;

import com.taller1.taller1.dtos.EmpleadoDTO;
import com.taller1.taller1.models.Cargo;
import com.taller1.taller1.models.Empleado;
import com.taller1.taller1.models.Oficina;
import com.taller1.taller1.repositoryes.CargoRepository;
import com.taller1.taller1.repositoryes.OficinaRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class EmpleadoMapperImpl implements EmpleadoMapper {

    private final CargoRepository cargoRepository;
    private final OficinaRepository oficinaRepository;

    public EmpleadoMapperImpl(CargoRepository cargoRepository, OficinaRepository oficinaRepository) {
        this.cargoRepository = cargoRepository;
        this.oficinaRepository = oficinaRepository;
    }

    @Override
    public Empleado toEntity(EmpleadoDTO dto) {
        Empleado empleado = new Empleado();
        empleado.setId(dto.getId());
        empleado.setNom(dto.getNombre());
        empleado.setApe(dto.getApellido());
        empleado.setDir(dto.getDireccion());
        empleado.setTel(dto.getTelefono());

        Cargo cargo = cargoRepository.findById(dto.getIdCargo())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrado"));
        empleado.setCargo(cargo);

        Oficina oficina = oficinaRepository.findById(dto.getIdOficina())
                .orElseThrow(() -> new EntityNotFoundException("Oficina no encontrada"));
        empleado.setOficina(oficina);

        return empleado;
    }

    @Override
    public EmpleadoDTO toDTO(Empleado entity) {
        return new EmpleadoDTO(
                entity.getId(),
                entity.getNom(),
                entity.getApe(),
                entity.getDir(),
                entity.getTel(),
                entity.getCargo().getId(),
                entity.getCargo().getCargo(),
                entity.getOficina().getId(),
                entity.getOficina().getNombre());
    }
}