package com.taller1.taller1.controllers;

import java.util.List;

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

import com.taller1.taller1.dtos.ProyectoDTO;
import com.taller1.taller1.services.ProyectoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    /**
     * POST /api/proyectos
     * Crea un nuevo proyecto.
     * 
     * @return 201 Created con el proyecto guardado
     */
    @PostMapping
    public ResponseEntity<ProyectoDTO> guardar(@Valid @RequestBody ProyectoDTO dto) {
        ProyectoDTO guardado = proyectoService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    /**
     * GET /api/proyectos/{id}
     * Busca un proyecto por ID.
     * return 200 OK si existe, 500 "proyecto no encontrado" si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProyectoDTO> buscarPorId(@PathVariable Long id) {
        ProyectoDTO dto = proyectoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * GET /api/proyectos
     * Lista todos los proyectos.
     * return 200 OK con lista de proyectos
     */
    @GetMapping
    public ResponseEntity<List<ProyectoDTO>> listarTodos() {
        List<ProyectoDTO> proyectos = proyectoService.listarTodos();
        return ResponseEntity.ok(proyectos);
    }

    /**
     * DELETE /api/proyectos/{id}
     * Elimina un proyecto por ID.
     * return 204 No Content si se elimina, 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proyectoService.eliminar(id);
        return ResponseEntity.noContent().build(); // HTTP 204 si se elimina correctamente
    }

    /*
     * PUT /api/proyectos/{id}
     * Actualiza un proyecto existente.
     * 
     * @return 200 OK si se actualiza, 404 si no existe
     */

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProyectoDTO dto) {
        ProyectoDTO actualizado = proyectoService.actualizar(id, dto);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
}
