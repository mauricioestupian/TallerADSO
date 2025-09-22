package com.taller1.taller1.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.taller1.dtos.EmpleadoAsignadoDTO;
import com.taller1.taller1.dtos.EmpleadoProyectoDTO;
import com.taller1.taller1.dtos.ProyectoAsignadoDTO;
import com.taller1.taller1.dtos.RespAsignacionMasivaDTO;
import com.taller1.taller1.dtos.ResultadoAsignacionDTO;
import com.taller1.taller1.services.EmpleadoProyectoService;

import jakarta.validation.Valid;

@RestController
// Controlador para gestionar las asignaciones de empleados a proyectos
// Mapea las solicitudes HTTP a /api/asignaciones con los métodos
// correspondientes url de la clase
@RequestMapping("/api/asignaciones")
public class EmpleadoProyectoController {

    private final EmpleadoProyectoService service;

    public EmpleadoProyectoController(EmpleadoProyectoService service) {
        this.service = service;
    }

    // POST para asignar un empleado a un proyecto
    @PostMapping
    public ResponseEntity<EmpleadoProyectoDTO> asignar(@Valid @RequestBody EmpleadoProyectoDTO dto) {

        // Lógica de negocio: delega la asignación al servicio
        EmpleadoProyectoDTO asignado = service.asignar(dto);

        // Devuelve una respuesta HTTP 200 OK con el DTO asignado como cuerpo
        return ResponseEntity.ok(asignado);
    }

    // POST para asignaciones múltiples
    @PostMapping("/varios")
    public ResponseEntity<List<ResultadoAsignacionDTO>> asignarMasivoNormal(
            @RequestBody List<EmpleadoProyectoDTO> asignaciones) {
        // presenta los resultados detallados de cada asignación individual
        List<ResultadoAsignacionDTO> resultados = service.asignarEmpleadosNormal(asignaciones);
        return ResponseEntity.ok(resultados);
    }

    // lo mismo que el anterior pero con el nuevo DTO de respuesta masiva
    // especificando totales
    // revisar la respuesta para comprender mejor su funcionamiento
    @PostMapping("/varios2")
    public ResponseEntity<RespAsignacionMasivaDTO> asignarMasivo(
            @RequestBody List<EmpleadoProyectoDTO> asignaciones) {
        // presenta los resultados detallados de cada asignación individual
        // devuelve totales de asignaciones, duplicados y errores
        RespAsignacionMasivaDTO response = service.asignarEmpleados(asignaciones);
        return ResponseEntity.ok(response);
    }

    // Consultar asignaciones por empleado
    // expone data del empleado y del proyecto de manera innesesaria
    @GetMapping("/empleado/{id}")
    public ResponseEntity<List<EmpleadoProyectoDTO>> listarPorEmpleado(@PathVariable Long id) {

        // Llama al servicio para obtener la lista de asignaciones del empleado
        List<EmpleadoProyectoDTO> asignaciones = service.listarPorEmpleado(id);

        // Devuelve una respuesta HTTP 200 OK con la lista en el cuerpo
        return ResponseEntity.ok(asignaciones);
    }

    // Consultar asignaciones por empleado
    // expone solo la data del proyecto asignado
    @GetMapping("/empleado2/{id}")
    public ResponseEntity<List<ProyectoAsignadoDTO>> listarPorEmpleado2(@PathVariable Long id) {

        // Llama al servicio para obtener la lista de proyectos asignados al empleado
        List<ProyectoAsignadoDTO> proyectos = service.listarPorEmpleado2(id);

        // Devuelve una respuesta HTTP 200 OK con la lista en el cuerpo
        return ResponseEntity.ok(proyectos);
    }

    // Consultar asignaciones por proyecto
    @GetMapping("/proyecto/{id}")
    public ResponseEntity<List<EmpleadoProyectoDTO>> listarPorProyecto(@PathVariable Long id) {

        // Llama al servicio para obtener la lista de asignaciones del proyecto
        List<EmpleadoProyectoDTO> asignaciones = service.listarPorProyecto(id);

        // Devuelve una respuesta HTTP 200 OK con la lista en el cuerpo
        return ResponseEntity.ok(asignaciones);
    }

    // Consultar asignaciones por proyecto (expone solo la data del empleado
    // asignado)
    @GetMapping("/proyecto2/{id}")
    public ResponseEntity<List<EmpleadoAsignadoDTO>> listarPorProyecto2(@PathVariable Long id) {

        // Llama al servicio para obtener la lista de empleados asignados al proyecto
        List<EmpleadoAsignadoDTO> empleados = service.listarPorProyecto2(id);

        // Devuelve una respuesta HTTP 200 OK con la lista en el cuerpo
        return ResponseEntity.ok(empleados);
    }

    // Eliminar asignación específica
    @DeleteMapping("/{empleadoId}/{proyectoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long empleadoId, @PathVariable Long proyectoId) {

        // Llama al servicio para eliminar la asignación
        service.eliminarAsignacion(empleadoId, proyectoId);

        // Devuelve una respuesta HTTP 204 No Content si la eliminación fue exitosa
        return ResponseEntity.noContent().build();
    }

}