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

import com.taller1.taller1.dtos.EmpleadoProyectoDTO;
import com.taller1.taller1.services.EmpleadoProyectoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/asignaciones")
public class EmpleadoProyectoController {

    private final EmpleadoProyectoService service;

    public EmpleadoProyectoController(EmpleadoProyectoService service) {
        this.service = service;
    }

    // 🟢 Asignar empleado a proyecto
    @PostMapping
    public ResponseEntity<EmpleadoProyectoDTO> asignar(@Valid @RequestBody EmpleadoProyectoDTO dto) {
        EmpleadoProyectoDTO asignado = service.asignar(dto);
        return ResponseEntity.ok(asignado);
    }

    @PostMapping("/varios")
    public ResponseEntity<List<EmpleadoProyectoDTO>> asignarMasivo(
            @RequestBody List<EmpleadoProyectoDTO> asignaciones) {
        List<EmpleadoProyectoDTO> emplePro = service.asignarEmpleados(asignaciones);
        return ResponseEntity.ok(emplePro);
    }

    // 🔍 Consultar asignaciones por empleado
    @GetMapping("/empleado/{id}")
    public ResponseEntity<List<EmpleadoProyectoDTO>> listarPorEmpleado(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPorEmpleado(id));
    }

    // 🔍 Consultar asignaciones por proyecto
    @GetMapping("/proyecto/{id}")
    public ResponseEntity<List<EmpleadoProyectoDTO>> listarPorProyecto(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPorProyecto(id));
    }

    // ❌ Eliminar asignación específica
    @DeleteMapping("/{empleadoId}/{proyectoId}")
    public ResponseEntity<Void> eliminar(@PathVariable Long empleadoId, @PathVariable Long proyectoId) {
        service.eliminarAsignacion(empleadoId, proyectoId);
        return ResponseEntity.noContent().build();
    }
}