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

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

// Anotación @Service indica que esta clase es un servicio de negocio en Spring "Logica de negocio"
@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    // Repositorio principal para acceder a los datos de la entidad Empleado.
    private final EmpleadoRepository empleadoRepository;

    // Repositorio para manejar las asignaciones entre empleados y proyectos.
    private final EmpleadoProyectoRepository empleadoProyectoRepository;

    // Mapper encargado de convertir entre entidades y DTOs.
    private final EmpleadoMapper empleadoMapper;

    // Repositorio para recuperar la entidad Cargo desde la base de datos.
    private final CargoRepository cargoRepository;

    // Repositorio para recuperar la entidad Oficina desde la base de datos.
    private final OficinaRepository oficinaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // Inyección por constructor: Spring proporciona automáticamente las
    // dependencias necesarias.
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository,
            EmpleadoProyectoRepository empleadoProyectoRepository,
            EmpleadoMapper empleadoMapper, CargoRepository cargoRepository, OficinaRepository oficinaRepository) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoProyectoRepository = empleadoProyectoRepository;
        this.empleadoMapper = empleadoMapper;
        this.cargoRepository = cargoRepository;
        this.oficinaRepository = oficinaRepository;
    }

    /**
     * Guarda un nuevo empleado en la base de datos.
     * uso @transactional con el fin de gestionar la transacción de forma
     * automática.
     * Convierte el DTO de entrada en una entidad, la guarda, y devuelve el DTO de
     * respuesta.
     */
    @Override
    @Transactional
    public EmpleadoDTO guardar(EmpleadoCreateDTO dto) {
        Empleado empleado = empleadoMapper.toEmpleado(dto);// convierte DTO a entidad
        Empleado guardado = empleadoRepository.save(empleado);// manda a guardar la entidad
        return empleadoMapper.toDTO(guardado);
    }

    /**
     * Busca un empleado por su ID.
     * Se usa @Transactional(readOnly = true) para optimizar la operación de solo
     * lectura.
     * orElseThrow lanza una excepción si no se encuentra el empleado.
     * EntityNotFoundException indica que el recurso solicitado no existe.
     */
    @Override
    @Transactional(readOnly = true)
    public EmpleadoDTO buscarPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
        return empleadoMapper.toDTO(empleado);
    }

    /**
     * Lista todos los empleados registrados.
     * Se usa @Transactional(readOnly = true) para mejorar el rendimiento en
     * operaciones de consulta.
     * Convierte cada entidad en su correspondiente DTO.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EmpleadoDTO> listarTodos() {
        return empleadoRepository.findAll().stream()
                .map(empleadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Elimina un empleado por su ID.
     * Se usa @Transactional para asegurar que la eliminación de asignaciones y del
     * empleado
     * se realicen en una sola transacción.
     * Primero se eliminan las asignaciones relacionadas para evitar errores de
     * integridad referencial.
     * isEmpty se usa para verificar si hay asignaciones antes de intentar
     * borrarlas.
     */
    @Override
    @Transactional
    public void eliminar(Long id) {
        // 1. Recuperar el empleado desde la base de datos
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));

        empleado.getAsignaciones().clear(); // ← Aquí va

        // 3. Eliminar asignaciones explícitamente desde el repositorio
        List<EmpleadoProyecto> asignaciones = empleadoProyectoRepository.findByEmpleado(empleado);
        if (!asignaciones.isEmpty()) {
            for (EmpleadoProyecto asignacion : asignaciones) {
                asignacion.setEmpleado(null);
                asignacion.setProyecto(null);
            }
            empleadoProyectoRepository.deleteAll(asignaciones);
        }

        // 4. Eliminar el empleado
        empleadoRepository.delete(empleado);
    }

    /**
     * Actualiza los datos de un empleado existente.
     * Se usa @Transactional para asegurar que todos los cambios se apliquen de
     * forma consistente.
     * Recupera la entidad, modifica sus campos, y la guarda nuevamente.
     * También actualiza las relaciones con Cargo y Oficina.
     * orElseThrow se usa para validar la existencia de cada entidad relacionada.
     */
    @Override
    @Transactional
    public EmpleadoDTO actualizarEmpleado(EmpleadoUpdateDTO dto) {
        Empleado empleado = empleadoRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
        // el

        // Actualiza los campos básicos del empleado.
        empleado.setNom(dto.getNombre());
        empleado.setApe(dto.getApellido());
        empleado.setDir(dto.getDireccion());
        empleado.setTel(dto.getTelefono());

        Cargo cargo = cargoRepository.findById(dto.getIdCargo())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrado"));
        empleado.setCargo(cargo);

        // Oficina es opcional: solo se asigna si el ID está presente
        if (dto.getIdOficina() != null) {
            Oficina oficina = oficinaRepository.findById(dto.getIdOficina())
                    .orElseThrow(() -> new EntityNotFoundException("Oficina no encontrada"));
            empleado.setOficina(oficina);
        } else {
            // Permite eliminar la oficina asignada si el ID es nulo
            empleado.setOficina(null);
        }

        // Guarda los cambios y devuelve el DTO actualizado.
        Empleado actualizado = empleadoRepository.save(empleado);
        return empleadoMapper.toDTO(actualizado);
    }

}