package com.taller1.taller1.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.taller1.dtos.EmpleadoCreateDTO;
import com.taller1.taller1.dtos.EmpleadoDTO;
import com.taller1.taller1.dtos.EmpleadoUpdateDTO;
import com.taller1.taller1.services.EmpleadoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    // Crear empleado
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody EmpleadoCreateDTO dto) {
        try {
            EmpleadoDTO creado = empleadoService.guardar(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje", "Empleado creado exitosamente", "data", creado));
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("errores1", ex.getMessage()));
        } catch (Exception ex) {
            String detalle = ex.getMessage();
            if (detalle != null && detalle.contains("Cosnt_empleado_oficina")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                        "success",
                        "Error al crear el empleado",
                        "mensaje", "La oficina ya está asignada a otro empleado"));
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("errores2", "Error3 al crear el empleado", "detalle", ex.getMessage()));
        }
    }

    // Obtener por ID
    @Operation(summary = "Requiere Inicio sesión con usuario y contraseña")
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> obtenerPorId(@PathVariable Long id) {
        EmpleadoDTO empleado = empleadoService.buscarPorId(id);
        return ResponseEntity.ok(empleado);
    }

    // Listar todos

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> listarTodos() {
        List<EmpleadoDTO> empleados = empleadoService.listarTodos();
        return ResponseEntity.ok(empleados);
    }

    // Actualizar empleado
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> actualizar(@PathVariable Long id,
            @Valid @RequestBody EmpleadoUpdateDTO dto) {
        dto.setId(id); // Asegura que el ID del path se use
        EmpleadoDTO actualizado = empleadoService.actualizarEmpleado(dto);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}