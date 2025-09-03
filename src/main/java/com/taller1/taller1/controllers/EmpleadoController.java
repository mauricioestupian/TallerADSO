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

import com.taller1.taller1.dtos.EmpleadoDTO;
import com.taller1.taller1.services.EmpleadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    // 🔄 Crear o actualizar empleado
    @PostMapping
    public ResponseEntity<EmpleadoDTO> guardar(@Valid @RequestBody EmpleadoDTO dto) {
        EmpleadoDTO guardado = empleadoService.guardar(dto);
        return ResponseEntity.ok(guardado);
    }

    // 🔍 Consultar empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> buscarPorId(@PathVariable Long id) {
        EmpleadoDTO dto = empleadoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    // 📋 Listar todos los empleados
    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> listarTodos() {
        List<EmpleadoDTO> empleados = empleadoService.listarTodos();
        return ResponseEntity.ok(empleados);
    }

    // ❌ Eliminar empleado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
