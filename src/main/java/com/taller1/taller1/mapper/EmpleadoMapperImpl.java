package com.taller1.taller1.mapper;

import org.springframework.stereotype.Component;

import com.taller1.taller1.dtos.EmpleadoCreateDTO;
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
    public Empleado toEmpleado(EmpleadoCreateDTO dto) {
        if (dto == null)
            return null;

        Empleado empleado = new Empleado();
        empleado.setNom(dto.getNombre());
        empleado.setApe(dto.getApellido());
        empleado.setDir(dto.getDireccion());
        empleado.setTel(dto.getTelefono());

        // Cargo obligatorio
        Cargo cargo = cargoRepository.findById(dto.getIdCargo())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrado"));
        empleado.setCargo(cargo);

        // Oficina opcional
        if (dto.getIdOficina() != null) {
            Oficina oficina = oficinaRepository.findById(dto.getIdOficina())
                    .orElseThrow(() -> new EntityNotFoundException("Oficina no encontrada"));
            empleado.setOficina(oficina);
        }

        return empleado;
    }

    @Override
    public EmpleadoDTO toDTO(Empleado empleado) {
        if (empleado == null)
            return null;

        return new EmpleadoDTO(
                empleado.getId(),
                empleado.getNom(),
                empleado.getApe(),
                empleado.getDir(),
                empleado.getTel(),
                empleado.getCargo() != null ? empleado.getCargo().getId() : null,
                empleado.getCargo() != null ? empleado.getCargo().getCargo() : null,
                empleado.getOficina() != null ? empleado.getOficina().getId() : null,
                empleado.getOficina() != null ? empleado.getOficina().getNombre() : null);
    }
}