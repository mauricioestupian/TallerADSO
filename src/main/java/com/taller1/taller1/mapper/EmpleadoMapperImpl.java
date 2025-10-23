package com.taller1.taller1.mapper;

import org.springframework.stereotype.Component;

import com.taller1.taller1.dtos.EmpleadoCrearDTO;
import com.taller1.taller1.dtos.EmpleadoDTO;
import com.taller1.taller1.models.Cargo;
import com.taller1.taller1.models.Empleado;
import com.taller1.taller1.models.Oficina;
import com.taller1.taller1.repositoryes.CargoRepository;
import com.taller1.taller1.repositoryes.OficinaRepository;

import jakarta.persistence.EntityNotFoundException;

//Definimos esta clase como un componente de Spring para que se inyecte automáticamente
@Component
public class EmpleadoMapperImpl implements EmpleadoMapper {

    // Repositorios necesarios acceder a entidades relacionadas (Cargo y Oficina)
    // inyección por constructor; evita el @Autowired
    private final CargoRepository cargoRepository;
    private final OficinaRepository oficinaRepository;

    // Constructor con inyección de dependencias mediante el constructor para
    // acceder a los repositorios
    public EmpleadoMapperImpl(CargoRepository cargoRepository, OficinaRepository oficinaRepository) {
        this.cargoRepository = cargoRepository;
        this.oficinaRepository = oficinaRepository;
    }

    /**
     * recibe los datos a modo DTO de creación (EmpleadoCreateDTO) y los pasa a
     * entidad Empleado.
     * Este método se usa al registrar un nuevo empleado en el sistema.
     * Recupera las entidades relacionadas (Cargo y Oficina) desde la base de datos
     * para evitar errores de Hibernate por referencias transientes.
     */
    @Override
    public Empleado toEmpleado(EmpleadoCrearDTO dto) {
        if (dto == null)
            return null;

        // Crear nueva instancia de Empleado y asignar campos básicos
        Empleado empleado = new Empleado();

        empleado.setNom(dto.getNombre());
        empleado.setApe(dto.getApellido());
        empleado.setDir(dto.getDireccion());
        empleado.setTel(dto.getTelefono());
        // Asignar username directamente
        empleado.setUsername(dto.getUsername());

        // Asignar password sin codificar (se codifica en el servicio)
        empleado.setPassword(dto.getPassword());

        // Campo obligatorio: se recupera desde el repositorio cargoRepository para que
        // esté gestionado por Hibernate
        Cargo cargo = cargoRepository.findById(dto.getIdCargo())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontradosssss"));
        empleado.setCargo(cargo);

        // Oficina es opcional: solo se asigna si el ID está presente
        if (dto.getIdOficina() != null) {
            Oficina oficina = oficinaRepository.findById(dto.getIdOficina())
                    .orElseThrow(() -> new EntityNotFoundException("Oficina no encontrada"));
            empleado.setOficina(oficina);
        }

        return empleado;
    }

    /**
     * Convierte una entidad Empleado en un DTO de consulta (EmpleadoDTO).
     * Este método se usa para enviar datos al cliente de forma estructurada y
     * enriquecida.
     * Incluye nombres legibles de Cargo y Oficina, además de sus IDs.
     */
    @Override
    public EmpleadoDTO toDTO(Empleado empleado) {
        if (empleado == null)
            return null;

        return new EmpleadoDTO(
                empleado.getId(), // ID del empleado
                empleado.getNom(), // Nombre
                empleado.getApe(), // Apellido
                empleado.getDir(), // Dirección
                empleado.getTel(), // Teléfono

                // ID y nombre del cargo (si está presente)
                empleado.getCargo() != null ? empleado.getCargo().getId() : null,
                empleado.getCargo() != null ? empleado.getCargo().getCargo() : null,

                // ID y nombre de la oficina (si está presente)
                empleado.getOficina() != null ? empleado.getOficina().getId() : null,
                empleado.getOficina() != null ? empleado.getOficina().getNombre() : null);
    }
}