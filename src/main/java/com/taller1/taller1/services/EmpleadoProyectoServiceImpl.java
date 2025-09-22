package com.taller1.taller1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.taller1.taller1.dtos.EmpleadoAsignadoDTO;
import com.taller1.taller1.dtos.EmpleadoProyectoDTO;
import com.taller1.taller1.dtos.ProyectoAsignadoDTO;
import com.taller1.taller1.dtos.RespAsignacionMasivaDTO;
import com.taller1.taller1.dtos.ResultadoAsignacionDTO;
import com.taller1.taller1.mapper.EmpleadoProyectoMapper;
import com.taller1.taller1.models.EmpleadoProyecto;
import com.taller1.taller1.models.EmpleadoProyectoId;
import com.taller1.taller1.repositoryes.EmpleadoProyectoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmpleadoProyectoServiceImpl implements EmpleadoProyectoService {

    private final EmpleadoProyectoRepository repository;
    private final EmpleadoProyectoMapper mapper;

    public EmpleadoProyectoServiceImpl(EmpleadoProyectoRepository repository, EmpleadoProyectoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EmpleadoProyectoDTO asignar(EmpleadoProyectoDTO dto) {
        // Convierte el DTO recibido en una entidad JPA
        EmpleadoProyecto ep = mapper.toEmpleadoProyecto(dto);

        // Validación opcional: evita asignaciones duplicadas
        EmpleadoProyectoId id = new EmpleadoProyectoId(dto.getEmpleadoId(), dto.getProyectoId());
        if (repository.existsById(id)) {
            // Si ya existe la asignación, lanza una excepción
            // Esto debería ser capturado por un GlobalExceptionHandler para devolver un 409
            // Conflict
            throw new IllegalStateException("Ya existe una asignación para ese empleado y proyecto");
        }

        // Guarda la nueva asignación en la base de datos
        EmpleadoProyecto guardado = repository.save(ep);

        // Convierte la entidad guardada de vuelta a DTO para devolver al controlador
        return mapper.toDTO(guardado);
    }

    @Override
    public List<EmpleadoProyectoDTO> listarPorEmpleado(Long empleadoId) {
        return repository.findByEmpleado_Id(empleadoId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProyectoAsignadoDTO> listarPorEmpleado2(Long empleadoId) {
        return repository.findByEmpleado_Id(empleadoId)
                .stream()
                .map(mapper::toProyectoAsignadoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmpleadoProyectoDTO> listarPorProyecto(Long proyectoId) {
        return repository.findByProyecto_Id(proyectoId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmpleadoAsignadoDTO> listarPorProyecto2(Long proyectoId) {
        return repository.findByProyecto_Id(proyectoId)
                .stream()
                .map(mapper::toEmpleadoAsignadoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarAsignacion(Long empleadoId, Long proyectoId) {
        EmpleadoProyectoId id = new EmpleadoProyectoId(empleadoId, proyectoId);
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Asignación no encontrada");
        }
        repository.deleteById(id);
    }

    @Override
    public RespAsignacionMasivaDTO asignarEmpleados(List<EmpleadoProyectoDTO> asignaciones) {
        List<ResultadoAsignacionDTO> resultados = new ArrayList<>();
        int asignadas = 0, duplicadas = 0, errores = 0;

        for (EmpleadoProyectoDTO dto : asignaciones) {
            ResultadoAsignacionDTO resultado = new ResultadoAsignacionDTO();
            resultado.setEmpleadoId(dto.getEmpleadoId());
            resultado.setProyectoId(dto.getProyectoId());

            try {
                EmpleadoProyectoId id = new EmpleadoProyectoId(dto.getEmpleadoId(), dto.getProyectoId());
                if (repository.existsById(id)) {
                    resultado.setEstado("DUPLICADO");
                    resultado.setMensaje("Ya existe la asignación");
                    duplicadas++;
                } else {
                    EmpleadoProyecto ep = mapper.toEmpleadoProyecto(dto);
                    repository.save(ep);
                    resultado.setEstado("ASIGNADO");
                    resultado.setMensaje("Asignación exitosa");
                    asignadas++;
                }
            } catch (Exception e) {
                resultado.setEstado("ERROR");
                resultado.setMensaje(e.getMessage());
                errores++;
            }

            resultados.add(resultado);
        }

        RespAsignacionMasivaDTO response = new RespAsignacionMasivaDTO();
        response.setTotalSolicitudes(asignaciones.size());
        response.setTotalAsignadas(asignadas);
        response.setTotalDuplicadas(duplicadas);
        response.setTotalErrores(errores);
        response.setResultados(resultados);

        return response;
    }

    public List<ResultadoAsignacionDTO> asignarEmpleadosNormal(List<EmpleadoProyectoDTO> asignaciones) {
        List<ResultadoAsignacionDTO> resultados = new ArrayList<>();

        for (EmpleadoProyectoDTO dto : asignaciones) {
            ResultadoAsignacionDTO resultado = new ResultadoAsignacionDTO();
            resultado.setEmpleadoId(dto.getEmpleadoId());
            resultado.setProyectoId(dto.getProyectoId());

            try {
                EmpleadoProyectoId id = new EmpleadoProyectoId(dto.getEmpleadoId(), dto.getProyectoId());
                if (repository.existsById(id)) {
                    resultado.setEstado("DUPLICADO");
                    resultado.setMensaje("Ya existe la asignación");
                } else {
                    EmpleadoProyecto ep = mapper.toEmpleadoProyecto(dto);
                    repository.save(ep);
                    resultado.setEstado("ASIGNADO");
                    resultado.setMensaje("Asignación exitosa");
                }
            } catch (Exception e) {
                resultado.setEstado("ERROR");
                resultado.setMensaje(e.getMessage());
            }

            resultados.add(resultado);
        }

        return resultados;
    }

}